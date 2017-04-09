package com.dmnlk.blockimo.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int resultCode;
    private String message;
}
