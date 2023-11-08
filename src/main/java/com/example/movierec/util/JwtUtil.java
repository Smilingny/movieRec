package com.example.movierec.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.movierec.entity.User;


public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "ynu";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 7 * 24 * 60 * 60L;//单位为秒

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)// 添加头部
                //基本信息放到claims中
                .withClaim("id", user.getId())//userId
                .withClaim("userName", user.getName())//userName
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt;
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            return null;
        }
        return jwt.getClaims();
    }
}
