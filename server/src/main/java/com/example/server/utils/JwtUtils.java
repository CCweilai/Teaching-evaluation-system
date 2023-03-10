package com.example.server.utils;

import com.example.server.entity.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;

public class JwtUtils {
    // 7天过期
    private static long expire = 604800;
    // 32位密钥
    private static String secret = "sjdhdjqozjskejckdjqjeofklcdeaode";
    //生成token
    public String getToken(Admin admin){
        Date now = new Date();
        Date expiration = new Date(now.getTime()+1000*expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(admin.getName())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
}
