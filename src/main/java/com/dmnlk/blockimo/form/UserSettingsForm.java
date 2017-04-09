package com.dmnlk.blockimo.form;

import lombok.Data;

/**
 * form
 */
@Data
public class UserSettingsForm {
    private boolean autoBlock;
    private boolean verifiedBlock;
    private boolean friendBlock;
    private boolean followerBlock;
}
