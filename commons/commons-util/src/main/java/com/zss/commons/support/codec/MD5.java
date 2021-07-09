package com.zss.commons.support.codec;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

public class MD5 {

    public static String md5Password(String password, String salt){
        String pwd = DigestUtils.md5Hex(password);
        String data = String.format("%s-%s",pwd, salt);
        return DigestUtils.md5Hex(data);
    }


    public final static String md5Encode(String str){
        return DigestUtils.md5Hex(str.getBytes(StandardCharsets.UTF_8));
    }

}
