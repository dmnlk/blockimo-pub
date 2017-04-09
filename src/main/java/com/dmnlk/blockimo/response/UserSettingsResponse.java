package com.dmnlk.blockimo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * response
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class UserSettingsResponse extends BaseResponse {
    /**  */
    Boolean friendBlockFlg;

    /**  */
    Boolean followerBlockFlg;

    /**  */
    Boolean verifiedBlockFlg;

    /**  */
    Boolean autoBlockEnableFlg;
}
