package com.dmnlk.blockimo.service;

import com.dmnlk.blockimo.dao.TAccountBlockUserDao;
import com.dmnlk.blockimo.dao.TBlockQueueDao;
import com.dmnlk.blockimo.dao.TUserQueueDao;
import com.dmnlk.blockimo.dto.BlockDataModel;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.entity.TAccountBlockUser;
import com.dmnlk.blockimo.entity.TBlockQueue;
import com.dmnlk.blockimo.entity.TUserQueue;
import com.dmnlk.blockimo.response.UserInfoResponse;
import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * get user information
 */
@Component
public class UserInfoService {
    @Autowired
    private TAccountBlockUserDao tAccountBlockUserDao;
    @Autowired
    private TUserQueueDao tUserQueueDao;
    @Autowired
    private TBlockQueueDao tBlockQueueDao;

    public UserInfoResponse getUserinfo(TwitterAuthDto twitterAuthDto) {
        UserInfoResponse response = new UserInfoResponse();
        long twitterId = twitterAuthDto.getAccessToken().getUserId();
        int blockCount = tAccountBlockUserDao.countById(twitterId);
        response.setBlockCount(blockCount);
        int queueCount = tBlockQueueDao.countYetProcessQueueByTwitterId(twitterId);
        response.setWaitingBlockCount(queueCount);
        List<TUserQueue> tUserQueues = tUserQueueDao.selectByTwitterId(twitterId);
        List<String> registerUserNameList = tUserQueues.stream().map(e -> e.getRegisterScreenName()).collect(Collectors.toList());
        response.setWaitingUserQueueNameList(registerUserNameList);
        List<TBlockQueue> blockQueues = tBlockQueueDao.selectRecentBlockById(twitterId, SelectOptions.get().limit(100));
        List<String> recentBlockList = blockQueues.stream().filter(e -> StringUtils.isNotEmpty(e.getBlockUserScreenName())).map(e -> e.getBlockUserScreenName()).limit(10).collect(Collectors.toList());
        response.setRecentBlockAccountNameList(recentBlockList);

        return response;
    }

    public List<BlockDataModel> getUserDataDump(TwitterAuthDto twitterAuthDto) {
        List<BlockDataModel> list = new ArrayList<>();
        List<TAccountBlockUser> tAccountBlockUsers = tAccountBlockUserDao.selectUserAll(twitterAuthDto.getAccessToken().getUserId());
        tAccountBlockUsers.stream().forEach(e -> {
            BlockDataModel model = new BlockDataModel();
            model.setTwitterId(e.getBlockUserTwitterId());
            model.setUrl(String.format("https://twitter.com/intent/user?user_id=%d", e.getBlockUserTwitterId()));
            list.add(model);
        });

        return list;
    }
}
