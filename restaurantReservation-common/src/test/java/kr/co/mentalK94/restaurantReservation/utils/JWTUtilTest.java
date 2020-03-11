package kr.co.mentalK94.restaurantReservation.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

public class JWTUtilTest {

    @Test
    public void createToken() {
        JWTUtil jwtUtil = new JWTUtil();

        String token = jwtUtil.createToken("rlagksthf209", "123456");

        assertThat(token, containsString("."));
    }
}