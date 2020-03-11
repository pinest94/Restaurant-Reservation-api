package kr.co.mentalK94.restaurantReservation.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

public class JWTUtil {

    private Key key;

    public JWTUtil(String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String userId, String userPassword) {

        // TODO: USE JJWT

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("userPassword", userPassword)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }
}
