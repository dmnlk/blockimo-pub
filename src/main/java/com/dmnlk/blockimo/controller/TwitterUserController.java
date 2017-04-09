package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.response.TwitterUserShowResponse;
import com.dmnlk.blockimo.util.doma.BlockimoUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * Twitter User Search
 */
@RestController
@Log4j
public class TwitterUserController {
    @Autowired
    private TwitterAuthDto twitterAuthDto;

    @RequestMapping("/twitter/show")
    public TwitterUserShowResponse show(@RequestParam  String screenName) {
        TwitterUserShowResponse response = new TwitterUserShowResponse();
        AccessToken accessToken = twitterAuthDto.getAccessToken();
        Twitter twitter = new TwitterFactory().getInstance(accessToken);
        response.setResultCode(0);
        log.info(java.lang.String.format("search : account=%s, param=%s", accessToken.getScreenName(), screenName));
        String replaceTwitterUserString = BlockimoUtils.replaceTwitterUserString(screenName);
        log.info(java.lang.String.format("search : account=%s, param=%s", accessToken.getScreenName(), replaceTwitterUserString));
        try {
            User user = twitter.showUser(replaceTwitterUserString);
            response.setUser(user);
        } catch (TwitterException e) {
            response.setResultCode(1);
            response.setMessage(e.getErrorMessage());
        }

        return response;
    }

}

