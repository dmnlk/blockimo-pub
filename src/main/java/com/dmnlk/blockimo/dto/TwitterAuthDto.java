package com.dmnlk.blockimo.dto;


import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.Serializable;

/**
 * Twitter Auth
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = WebApplicationContext.SCOPE_SESSION)
@Data
public class TwitterAuthDto implements Serializable {
    private AccessToken accessToken;
    private RequestToken requestToken;
    private boolean isLogin;

    public void clear() {
        accessToken = null;
        requestToken = null;
        isLogin = false;
    }
}
