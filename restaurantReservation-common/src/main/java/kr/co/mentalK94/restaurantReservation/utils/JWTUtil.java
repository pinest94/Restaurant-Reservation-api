package kr.co.mentalK94.restaurantReservation.utils;

import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    public String createToken(String userId, String userPassword) {
        return "header.payload.signature";
    }
}
