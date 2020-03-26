package kr.co.mentalK94.restaurantReservation.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTUtil {

    private Key key;

    public JWTUtil(String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String email, String name) {

        // TODO: USE JJWT
        return Jwts.builder()
                .claim("email", email)
                .claim("name", name)
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
