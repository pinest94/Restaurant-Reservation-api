package kr.co.mentalK94.restaurantReservation.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTUtil {

    private Key key;

    public JWTUtil(String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Long userId, String name, Long restaurantId) {

        // TODO: USE JJWT
        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name);

        if(restaurantId != null) { // 가게 주인인 경우 restaurantId가 존재하므로 추가
             jwtBuilder.claim("restaurantId", restaurantId);
        }
        return jwtBuilder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
