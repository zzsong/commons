package com.zss.commons.support.codec;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordMD5 {

    public static String md5(String password, String salt){
        String pwd = DigestUtils.md5Hex(password);
        String data = String.format("%s-%s",pwd, salt);
        return DigestUtils.md5Hex(data);
    }

}
