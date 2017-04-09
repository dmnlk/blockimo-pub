package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.response.IndexResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Log4j
public class SampleController {

    private IndexResponse response;

    @ResponseBody
    @RequestMapping("/sample/index")
    public IndexResponse index() {
        log.info("start");
        IndexResponse response = new IndexResponse();
        response.setMessage("fusk");
        log.info("end");
        return  response;
    }


    @RequestMapping("/sample/form")
    public String form(Model model) {
        response = new IndexResponse();
        response.setMessage("fuck");
        model.addAttribute("response", response);
        return  "hello";
    }
}
