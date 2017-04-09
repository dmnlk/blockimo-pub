package com.dmnlk.blockimo.logic;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterLogic {

    public Twitter createTwitterByUser(String accessTokenString, String accessTokenSecretString) {
        AccessToken accessToken = new AccessToken(accessTokenString, accessTokenSecretString);
        Twitter twitter = new TwitterFactory().getInstance(accessToken);
        return twitter;
    }
}
