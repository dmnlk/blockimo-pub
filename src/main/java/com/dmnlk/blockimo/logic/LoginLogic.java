package com.dmnlk.blockimo.logic;

import com.dmnlk.blockimo.entity.TAccount;
import com.dmnlk.blockimo.service.AccountService;
import com.dmnlk.blockimo.util.doma.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.auth.AccessToken;

import java.time.LocalDateTime;

/**
 *
 */
@Component
public class LoginLogic {
    @Autowired
    private AccountService accountService;

    public TAccount login(AccessToken accessToken) {
        LocalDateTime now = DateUtil.getNowTimestamp();
        long userId = accessToken.getUserId();

        TAccount tAccount = accountService.findTAccount(userId);
        if (tAccount == null) {
            tAccount = new TAccount();
            tAccount.setTwitterId(userId);
            tAccount.setAccessToken(accessToken.getToken());
            tAccount.setAccessTokenSecret(accessToken.getTokenSecret());
            tAccount.setScreenId(accessToken.getScreenName());
            tAccount.setNextBatchExecuteDate(now);
            tAccount.setBlockUserCursor(-1L);
            tAccount.setFollowerBlockFlg(false);
            tAccount.setFriendBlockFlg(false);
            tAccount.setVerifiedBlockFlg(false);
            tAccount.setAutoBlockEnableFlg(true);
            tAccount.setAddDate(now);
            tAccount.setUpdateDate(now);

            accountService.tAccountInsert(tAccount);
        } else {
            tAccount.setAccessToken(accessToken.getToken());
            tAccount.setAccessTokenSecret(accessToken.getTokenSecret());
            tAccount.setScreenId(accessToken.getScreenName());
            tAccount.setUpdateDate(now);

            accountService.tAccountUpdate(tAccount);
        }

        return tAccount;
    }
}
