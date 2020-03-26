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

        String token = jwtUtil.createToken("doingnow94@gmail.com", "hansol");

        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJybGFna3N0aGYyMDkiLCJuYW1lIjoiaGFuc29sIn0.WjDAJRtp4qYo-5WZ0bgctaGobn6SsrtvXBy0mE25uwQ";

        Claims claims = jwtUtil.getClaims(token);
        
        assertThat(claims.get("name"), is("hansol"));
    }
}