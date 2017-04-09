package com.dmnlk.blockimo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import twitter4j.User;

@Data
@EqualsAndHashCode(callSuper =  false)
public class TwitterUserShowResponse extends BaseResponse {
    private User user;
}
