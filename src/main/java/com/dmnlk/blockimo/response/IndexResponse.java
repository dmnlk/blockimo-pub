package com.dmnlk.blockimo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper =  false)
public class IndexResponse extends BaseResponse {
    private String message;
}
