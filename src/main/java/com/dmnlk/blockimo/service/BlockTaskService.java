package com.dmnlk.blockimo.service;

import com.dmnlk.blockimo.domain.QueueStatus;
import com.dmnlk.blockimo.entity.TAccount;
import com.dmnlk.blockimo.entity.TAccountBlockUser;
import com.dmnlk.blockimo.entity.TBlockQueue;
import com.dmnlk.blockimo.entity.TUserQueue;
import com.dmnlk.blockimo.logic.TwitterLogic;
import com.dmnlk.blockimo.util.doma.DateUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j
public class BlockTaskService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private TwitterLogic twitterLogic;



    public List<TAccount> firstStep() {
        // fetch all user from tAccount
        List<TAccount> tAccountList = accountService.findAll();
        if (CollectionUtils.isEmpty(tAccountList)) {
            return null;
        }

        return tAccountList;
    }

    public void secondStep(TAccount e) {
        List<TBlockQueue> allAccountBlockQueue = blockService.findAllAccountBlockQueue(e.getTwitterId());
        if (CollectionUtils.isNotEmpty(allAccountBlockQueue)) {
            log.info("already have queue skip");
            return;
        }

        List<TUserQueue> allUserQueue = blockService.findAllUserQueue(e.getTwitterId());
        if (CollectionUtils.isNotEmpty(allUserQueue)) {
            allUserQueue.forEach(q -> registerBlockQueue(e,q));
        }
    }

    private void registerBlockQueue(TAccount tAccount, TUserQueue tUserQueue) {
        LocalDateTime now = DateUtil.getNowTimestamp();
        List<TBlockQueue> blockQueues = new ArrayList<>();
        Twitter twitter = twitterLogic.createTwitterByUser(tAccount.getAccessToken(), tAccount.getAccessTokenSecret());
        Long twitterId = tAccount.getTwitterId();

        Long registerTwitterId = tUserQueue.getRegisterTwitterId();
        try {
            twitter.showUser(registerTwitterId);
        } catch (TwitterException e) {
            if (e.getErrorCode() == 63 || e.getErrorCode() == 50) {
                blockService.deleteUserQueue(tUserQueue);
                return;
            }
            return;
        }
        long cursor = tUserQueue.getNextCursor();
        boolean isAbort = false;
        int limitter = 0;
        do {
            try {
                IDs followersIDs = twitter.getFollowersIDs(registerTwitterId, cursor);
                for (long id : followersIDs.getIDs()) {
                    TBlockQueue tb = blockService.findByTwitterIdAndBlockTwitterId(twitterId, id);
                    if (tb != null) {
                        // already queue
                        continue;
                    }

                    tb = new TBlockQueue();
                    tb.setTwitterId(twitterId);
                    tb.setQueueStatus(QueueStatus.QUEUE);
                    tb.setBlockUserTwitterId(id);
                    tb.setAddDate(now);
                    tb.setUpdateDate(now);
                    blockQueues.add(tb);
                }

                log.info(String.format("cursor = %s", cursor));
                cursor = followersIDs.getNextCursor();
                limitter++;
                log.info(String.format("next cursor = %s", cursor));

                if (cursor != 0 && limitter == 1) {
                    isAbort = true;
                    break;
                }
            } catch (TwitterException e) {
                log.info(e.getErrorMessage());
                isAbort = true;
                break;
            }
        } while (cursor > 0);

        if (isAbort) {
            tUserQueue.setNextCursor(cursor);
            tUserQueue.setUpdateDate(now);
            blockService.updateTUserQueue(tUserQueue);
        } else {
            blockService.deleteUserQueue(tUserQueue);
        }

        if (CollectionUtils.isNotEmpty(blockQueues)) {
            log.info(String.format("register : %d", blockQueues.size()));
            blockService.registerBlockUser(blockQueues);
        }
    }


    public void thirdStep(TAccount e) {

        if (e.getAutoBlockEnableFlg() == false) {
            log.info(String.format( "skip auto block process  account=%s", e.getScreenId()));
            return;
        }

        log.info(String.format( "start step account=%s", e.getScreenId()));

        // fetch tblockqueue
        List<TBlockQueue> accountBlockQueue = blockService.findAllAccountBlockQueue(e.getTwitterId());
        if (CollectionUtils.isEmpty(accountBlockQueue)) {
            log.info(String.format("finish step account=%s queue is nothing", e.getScreenId()));
            return;
        }


        Twitter twitter = twitterLogic.createTwitterByUser(e.getAccessToken(), e.getAccessTokenSecret());
        long cursor = -1L;
        Set<Long> friendIds = new HashSet<>();
        if (e.getFriendBlockFlg() == false) {
            do {
                IDs friendsIDs;
                try {
                    friendsIDs = twitter.getFriendsIDs(twitter.getId(), cursor);
                    for (long id : friendsIDs.getIDs()) friendIds.add(id);
                    cursor = friendsIDs.getNextCursor();
                } catch (TwitterException e1) {
                    log.info(e1.getErrorMessage());
                    return;
                }
            }
            while (cursor > 0);
        }
        cursor = -1L;
        Set<Long> followerIds = new HashSet<>();
        if (e.getFollowerBlockFlg() == false) {
            do {
                IDs followers;
                try {
                    followers = twitter.getFollowersIDs(twitter.getId(), cursor);
                    for (long id : followers.getIDs()) followerIds.add(id);
                    cursor = followers.getNextCursor();
                } catch (TwitterException e1) {
                    log.info(e1.getErrorMessage());
                    return;
                }
            }
            while (cursor > 0);
        }

        boolean skipFlg = false;
        List<TBlockQueue> updateEntityList = new ArrayList<>();
        List<TAccountBlockUser> insertBlockUserList = new ArrayList<>();
        LocalDateTime now = DateUtil.getNowTimestamp();
        for (TBlockQueue b : accountBlockQueue) {

            // skip already block account
            Long blockUserTwitterId = b.getBlockUserTwitterId();
            TAccountBlockUser tAccountBlockUser = blockService.findTAccountBlockUserById(b.getTwitterId(), b.getBlockUserTwitterId());
            if (tAccountBlockUser != null) {
                log.info(String.format("skip is %s already block account", blockUserTwitterId));
                b.setQueueStatus(QueueStatus.SKIP);
                updateEntityList.add(b);
                continue;
            }

            // skip friend account
            if (CollectionUtils.isNotEmpty(friendIds) && friendIds.contains(blockUserTwitterId)) {
                log.info(String.format("skip is %s friend Account", blockUserTwitterId));
                b.setQueueStatus(QueueStatus.SKIP);
                updateEntityList.add(b);
                continue;
            }

            if (CollectionUtils.isNotEmpty(followerIds) && followerIds.contains(blockUserTwitterId)) {
                log.info(String.format("skip is %s follower Account", blockUserTwitterId));
                b.setQueueStatus(QueueStatus.SKIP);
                updateEntityList.add(b);
                continue;
            }

            // check user profile
            User user = null;
            try {
                user = twitter.showUser(blockUserTwitterId);

            } catch (TwitterException e1) {
                log.info(e1.getErrorMessage());
                // if user is suspend or not found, change queue status
                if (e1.getErrorCode() == 63) {
                    log.info(String.format("skip %s is suspend Account", blockUserTwitterId));
                    b.setQueueStatus(QueueStatus.SKIP);
                    updateEntityList.add(b);
                    continue;
                }

                if (e1.getErrorCode() == 50) {
                    log.info(String.format("skip %s is not found", blockUserTwitterId));
                    b.setQueueStatus(QueueStatus.SKIP);
                    updateEntityList.add(b);
                    continue;
                }
                skipFlg = true;
            }

            if (skipFlg) break;

            // skip if user is follower or verify account
            if (e.getVerifiedBlockFlg() == false && user.isVerified()) {
                log.info(String.format("skip %s is verified Account", user.getScreenName()));
                b.setBlockUserScreenName(user.getScreenName());
                b.setQueueStatus(QueueStatus.SKIP);
                updateEntityList.add(b);
                continue;
            }

            // block user
            try {
                twitter.createBlock(user.getId());
            } catch (TwitterException e1) {
                log.info(e1.getErrorMessage());
                skipFlg = true;
                continue;
            }

            log.info(String.format("blocked %s by %s", user.getScreenName(), e.getScreenId()));

            b.setQueueStatus(QueueStatus.DONE);
            b.setBlockUserScreenName(user.getScreenName());


            tAccountBlockUser = new TAccountBlockUser();
            tAccountBlockUser.setTwitterId(b.getTwitterId());
            tAccountBlockUser.setBlockUserTwitterId(b.getBlockUserTwitterId());
            tAccountBlockUser.setAddDate(now);
            tAccountBlockUser.setUpdateDate(now);
            insertBlockUserList.add(tAccountBlockUser);
            updateEntityList.add(b);

        }
        // update list per user
        updateEntityList.forEach(b -> b.setUpdateDate(now));
        blockService.updateTBlockQueueList(updateEntityList);

        if (CollectionUtils.isNotEmpty(insertBlockUserList)) {

           // List<TAccountBlockUser> collect = insertBlockUserList.stream().distinct().collect(Collectors.toList());
            Collection<TAccountBlockUser> values = insertBlockUserList.stream().collect(Collectors.toMap(TAccountBlockUser::getBlockUserTwitterId, p -> p, (p, q) -> p)).values();
            List<TAccountBlockUser> collect = values.stream().collect(Collectors.toList());
            blockService.insertsTAccountBlockUser(collect);
        }

        log.info(String.format("finish step account=%s block count=%d", e.getScreenId(),updateEntityList.size()));
    }


    public void registerBlockUser(TAccount tAccount) {
        if (tAccount.getBlockUserCursor() == 0L) return;

        Twitter twitter = twitterLogic.createTwitterByUser(tAccount.getAccessToken(), tAccount.getAccessTokenSecret());
        long cursor = tAccount.getBlockUserCursor();

        Set<Long> blockSet = new HashSet<>();
        List<TAccountBlockUser> blockUserList = new ArrayList<>();
        Long twitterId = tAccount.getTwitterId();
        LocalDateTime now = DateUtil.getNowTimestamp();
        do {
            try {
                IDs blocksIDs = twitter.getBlocksIDs(cursor);
                for (long id : blocksIDs.getIDs())  {
                    TAccountBlockUser tAccountBlockUser = blockService.findTAccountBlockUserById(twitterId, id);
                    if (tAccountBlockUser == null) {
                        tAccountBlockUser = new TAccountBlockUser();
                        tAccountBlockUser.setTwitterId(twitterId);
                        tAccountBlockUser.setBlockUserTwitterId(id);
                        tAccountBlockUser.setAddDate(now);
                        tAccountBlockUser.setUpdateDate(now);
                        blockUserList.add(tAccountBlockUser);
                    }

                }
                log.info(String.format("cursor = %s", cursor));
                cursor = blocksIDs.getNextCursor();
                log.info(String.format("next cursor = %s", cursor));
            } catch (TwitterException e1) {
                log.info(e1.getErrorMessage());
                break;
            }
        } while (cursor > 0);
        tAccount.setBlockUserCursor(cursor);
        accountService.tAccountUpdate(tAccount);
        if (CollectionUtils.isNotEmpty(blockUserList)) {
            blockService.insertsTAccountBlockUser(blockUserList);
        }
    }

    public void notifyToAccount(TAccount tAccount) {
        Twitter twitter = twitterLogic.createTwitterByUser(tAccount.getAccessToken(), tAccount.getAccessTokenSecret());
        int blockCount = blockService.countTodayBlock(tAccount.getTwitterId());
        if (blockCount > 0){
            String str = String.format("%s は昨日およそ %dアカウントをブロックしました", tAccount.getScreenId(), blockCount);
            log.info(str);
            try {
                twitter.sendDirectMessage(twitter.getId(), str);
            } catch (TwitterException e) {
                log.info(e.getErrorMessage());
            }
        }
    }
}
