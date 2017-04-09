package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.form.UserSettingsForm;
import com.dmnlk.blockimo.response.UserSettingsResponse;
import com.dmnlk.blockimo.service.UserSettingsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * user setting page
 */
@Controller
@Component
@Log4j
public class UserSettingsContoller {
    @Autowired
    private TwitterAuthDto twitterAuthDto;
    @Autowired
    private UserSettingsService userSettingsService;

    @RequestMapping("/settings/account")
    public String index(Model model) {
        UserSettingsResponse response = userSettingsService.getSettingsInformation(twitterAuthDto);
        model.addAttribute("response", response);
        return "user/settings_account";
    }

    @RequestMapping("/settings/account/update")
    @ResponseBody
    public UserSettingsResponse update(@ModelAttribute UserSettingsForm form) {
        UserSettingsResponse response = userSettingsService.update(twitterAuthDto, form);
        response.setResultCode(0);
        return response;
    }

    @RequestMapping("/settings/delete")
    public String delete() {
        userSettingsService.deleteAccount(twitterAuthDto);
        twitterAuthDto.clear();
        return "redirect:index";
    }

}
