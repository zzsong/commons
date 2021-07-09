package com.zss.commons.support.token;

public class Test {
    public static void main(String[] args) {
        JWTConfig config = new JWTConfig();
        config.setSecret("1qaz2wsx");
        config.setTokenHeader("token");
        config.setTokenPrefix("token");
        config.setExpireSecond(30);
        TokenOption tokenOption = new TokenOption();
        tokenOption.setTid("123456789");
        tokenOption.setSubject("subject");

        String token = JWTToken.builder(config).builderToken(tokenOption);
        System.out.println(token);

        System.out.println("xxxxxxxxxxxxxx");

        System.out.println(JWTToken.builder(config).authenticationToken(token, tokenOption));
    }
}
