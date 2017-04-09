package com.dmnlk.blockimo.util.doma;

public class  BlockimoUtils {

    public static String replaceTwitterUserString(String inputUserName) {
        String replaced = inputUserName.replaceAll("[^0-9a-zA-Z_]", "");
        return replaced;
    }
}
