package com.dmnlk.blockimo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * response /user/index
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class UserInfoResponse extends BaseResponse {
    private int blockCount;
    private int waitingBlockCount;
    private List<String> waitingUserQueueNameList;
    private List<String> recentBlockAccountNameList;
 }
