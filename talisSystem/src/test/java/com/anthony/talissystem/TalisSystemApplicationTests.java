package com.anthony.talissystem;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class TalisSystemApplicationTests {

    @Test
    public void testUUid(){
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

//    @Test
//    public void testGenJwt(){
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id",1);
//        claims.put("name","tom");
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256,"anthonybrown")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为1h
//                .compact();
//        System.out.println(jwt);
//    }
//
//    @Test
//    public void testParseJwt(){
//        Claims claims = Jwts.parser()
//                .setSigningKey("anthonybrown")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MDk0NTYzMX0.PVfnnkYI05EFgzBBU9UYnjqRmJ4G7zHto9g68wqIcUk")
//                .getBody();
//        System.out.println(claims);
//    }
}
