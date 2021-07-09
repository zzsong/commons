package com.zss.commons.support.token;


public class JWTConfig {
    /**密钥KEY**/
    private String secret;
    /**TokenKey**/
    private String tokenHeader;
    /**Token前缀字符**/
    private String tokenPrefix;
    /** 有效时长，秒**/
    private int expireSecond = 60;
    /**签发者**/
    private String issuer;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public int getExpireSecond() {
        return expireSecond;
    }

    public void setExpireSecond(Integer expireSecond) {
        this.expireSecond = expireSecond;
    }

    public void setExpireSecond(int expireSecond) {
        this.expireSecond = expireSecond;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
