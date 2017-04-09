package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.dto.BlockDataModel;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.response.UserInfoResponse;
import com.dmnlk.blockimo.service.UserInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  user information page
 */
@Controller
@Log4j
public class UserInfoContorller {
    @Autowired
    private TwitterAuthDto twitterAuthDto;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/user/index")
    public String index(Model model) {
        UserInfoResponse response = userInfoService.getUserinfo(twitterAuthDto);
        model.addAttribute("response", response);
        return "user/index";
    }

    @RequestMapping(value = "/user/download/data.csv"
    ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object download(HttpServletResponse response) throws JsonProcessingException {
        List<BlockDataModel> userDataDump = userInfoService.getUserDataDump(twitterAuthDto);
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(BlockDataModel.class).withHeader();
        return csvMapper.writer(schema).writeValueAsString(userDataDump);
    }

}
