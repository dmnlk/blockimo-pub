package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.annotaion.NonAuth;
import com.dmnlk.blockimo.config.EnvironmentConfig;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.logic.LoginLogic;
import com.dmnlk.blockimo.response.IndexResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.Date;
import java.util.Map;

/**
 * index
 */
@Controller
@Log4j
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndexController {
    @Autowired
    private TwitterAuthDto twitterAuthDto;
    @Autowired
    private LoginLogic loginLogic;
    @Autowired
    private EnvironmentConfig environmentConfig;

    @NonAuth
    @RequestMapping("/")
    public String index() {
        return "redirect:index";
    }

    @NonAuth
    @RequestMapping("/index")
    public String index(Model model,  @ModelAttribute("message") final String message, @ModelAttribute("error") final String errorMessage) {
        String path = "index";
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            RequestToken requestToken = twitter.getOAuthRequestToken(environmentConfig.getBaseUrl()+"/callback");
            String authenticationURL = requestToken.getAuthenticationURL();
            model.addAttribute("authUrl", authenticationURL);
            twitterAuthDto.setRequestToken(requestToken);

            IndexResponse response = new IndexResponse();
            model.addAttribute("response", response);
            if (errorMessage != null) {
                model.addAttribute("error", errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

        return path;
    }


    @RequestMapping("/callback")
    @NonAuth
    public String callback(Model model, @RequestParam Map<String, String> queryParameters, RedirectAttributes redirectAttributes) {
        String path = "index";
        IndexResponse response = new IndexResponse();
        response.setMessage(new Date().toString());
        model.addAttribute("response", response);
        log.info(queryParameters);
        Twitter twitter = new TwitterFactory().getInstance();
        RequestToken requestToken = twitterAuthDto.getRequestToken();
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, queryParameters.get("oauth_verifier"));
            log.info(accessToken.getScreenName());
            twitterAuthDto.setAccessToken(accessToken);
            twitterAuthDto.setLogin(true);
            loginLogic.login(accessToken);

            redirectAttributes.addFlashAttribute("message", String.format("%s としてログインに成功しました", accessToken.getScreenName()));
        } catch (Exception e) {
          redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:index";
    }


    @RequestMapping("/contact")
    @NonAuth
    public String contact(Model model) {
        String path = "contact";
        return path;
    }

    @RequestMapping("/logout")
    @NonAuth
    public String logout(Model model) {
        String path = "index";
        IndexResponse response = new IndexResponse();
        response.setMessage(new Date().toString());
        model.addAttribute("response", response);
        twitterAuthDto.clear();;
        return "redirect:index";
    }

    @RequestMapping("/qa")
    public String qa(Model model) {
        String path = "qa";
        return path;
    }
}
