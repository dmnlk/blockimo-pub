package com.dmnlk.blockimo.service;

import com.dmnlk.blockimo.dao.TAccountBlockUserDao;
import com.dmnlk.blockimo.dao.TAccountDao;
import com.dmnlk.blockimo.dao.TBlockQueueDao;
import com.dmnlk.blockimo.dao.TUserQueueDao;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.entity.TAccount;
import com.dmnlk.blockimo.form.UserSettingsForm;
import com.dmnlk.blockimo.response.UserSettingsResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * settings
 */
@Component
@Log4j
public class UserSettingsService {
    @Autowired
    private TAccountDao tAccountDao;
    @Autowired
    private TAccountBlockUserDao tAccountBlockUserDao;
    @Autowired
    private TBlockQueueDao tBlockQueueDao;
    @Autowired
    private TUserQueueDao tUserQueueDao;

    public UserSettingsResponse getSettingsInformation(TwitterAuthDto twitterAuthDto) {
        long twitterId = twitterAuthDto.getAccessToken().getUserId();
        TAccount tAccount = tAccountDao.selectById(twitterId);
        if (tAccount == null) {
            throw new IllegalArgumentException(String.format("Not found this account = %s", twitterAuthDto.getAccessToken().getScreenName()));
        }

        UserSettingsResponse response = new UserSettingsResponse();
        response.setAutoBlockEnableFlg(tAccount.getAutoBlockEnableFlg());
        response.setFollowerBlockFlg(tAccount.getFollowerBlockFlg());
        response.setFriendBlockFlg(tAccount.getFriendBlockFlg());
        response.setVerifiedBlockFlg(tAccount.getVerifiedBlockFlg());
        return response;
    }

    public UserSettingsResponse update(TwitterAuthDto twitterAuthDto, UserSettingsForm form) {
        long twitterId = twitterAuthDto.getAccessToken().getUserId();
        TAccount tAccount = tAccountDao.selectById(twitterId);
        if (tAccount == null) {
            throw new IllegalArgumentException(String.format("Not found this account = %s", twitterAuthDto.getAccessToken().getScreenName()));
        }

        tAccount.setAutoBlockEnableFlg(form.isAutoBlock());
        tAccount.setVerifiedBlockFlg(form.isVerifiedBlock());
        tAccount.setFriendBlockFlg(form.isFriendBlock());
        tAccount.setFollowerBlockFlg(form.isFollowerBlock());

        tAccountDao.update(tAccount);

        UserSettingsResponse response = new UserSettingsResponse();
        response.setAutoBlockEnableFlg(tAccount.getAutoBlockEnableFlg());
        response.setFollowerBlockFlg(tAccount.getFollowerBlockFlg());
        response.setFriendBlockFlg(tAccount.getFriendBlockFlg());
        response.setVerifiedBlockFlg(tAccount.getVerifiedBlockFlg());
        return response;
    }

    public void deleteAccount(TwitterAuthDto twitterAuthDto) {
        long twitterId = twitterAuthDto.getAccessToken().getUserId();
        TAccount tAccount = tAccountDao.selectById(twitterId);
        if (tAccount == null) {
            throw new IllegalArgumentException(String.format("Not found this account = %s", twitterAuthDto.getAccessToken().getScreenName()));
        }
        tAccountDao.delete(tAccount);

        // delete all info
        tAccountBlockUserDao.deleteAccountRecord(twitterId);
        tBlockQueueDao.deleteAccountRecord(twitterId);
        tUserQueueDao.deleteAccountRecord(twitterId);
    }
}
