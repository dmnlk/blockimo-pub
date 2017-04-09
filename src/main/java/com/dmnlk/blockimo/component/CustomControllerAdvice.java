package com.dmnlk.blockimo.component;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * some check for controller
 */
@ControllerAdvice
public class CustomControllerAdvice {
    public void initBinder(WebDataBinder binder) {

    }
}
