package kr.co.mentalK94.restaurantReservation.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JWTUtilTest {

    private static final String SECRET_KEY = "123456789987654321123456789987654321";

    private JWTUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JWTUtil(SECRET_KEY);
    }

    @Test
    public void createToken() {

        String token = jwtUtil.createToken(2020L, "Owner", 1004L);

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjYsIm5hbWUiOiLquYDtlZzshpQifQ.52oAO9QUJmq_wQPpLzF-tp2wqdweRv7c3gReapmThsY";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("name"), is("김한솔"));
    }
}