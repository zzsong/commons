package com.zss.commons.support.token;

import com.alibaba.fastjson.JSON;
import com.zss.commons.support.codec.MD5;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

public final class JWTToken {

    private final String TEMP = "temp";

    private JWTConfig jwtConfig;

    private static JWTToken jwtToken = new JWTToken();

    private JWTToken(){}

    public static JWTToken builder(JWTConfig config){
        jwtToken.jwtConfig = config;
        return jwtToken;
    }

    /**
     * 生成Token
     * @return
     */
    public String builderToken(TokenOption tokenOption){
        LocalDateTime dateTime = LocalDateTime.now().plusSeconds(jwtConfig.getExpireSecond());
        Date expireDate = Date.from(dateTime.toInstant(ZoneOffset.UTC));
        String uuid = UUID.randomUUID().toString();
        System.out.println("build uuid:\t"+uuid);
        String id = MD5.md5Encode(tokenOption.getTid());
        // 登陆成功生成JWT
        String token = Jwts.builder()
//                .setHeader(Jwts.jwsHeader().setKeyId(uuid).setType(""))
                // 放入核心数据进行加密验证
                .setId(id)
                .setSubject(MD5.md5Encode(tokenOption.getSubject()))
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer(jwtConfig.getIssuer())
                // 失效时间
                .setExpiration(expireDate)
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
        return StringUtils.join(jwtConfig.getTokenPrefix(),token);
    }

    public boolean authenticationToken(String token, TokenOption option){

        if (StringUtils.startsWith(token, jwtConfig.getTokenPrefix())){
            token = token.replace(jwtConfig.getTokenPrefix(), "");
        }
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token);

        System.out.println("type:\t"+jws.getHeader().getType());
        System.out.println("algorithm:\t"+jws.getHeader().getAlgorithm());
        System.out.println("contentType:\t"+jws.getHeader().getContentType());
        String uuid = jws.getHeader().getKeyId();
        System.out.println("uuid==\t"+uuid);
        // 解析JWT
        Claims claims = jws.getBody();
        Date expireDate = claims.getExpiration();
        if (new Date().after(expireDate)) {
            return false;
        }

        // 获取
        String subject = claims.getSubject();
        String tid=claims.getId();
        if (MD5.md5Encode(option.getTid()).equals(tid)
                && MD5.md5Encode(option.getSubject()).equals(subject)){
            return true;
        }
        return false;
    }
}
