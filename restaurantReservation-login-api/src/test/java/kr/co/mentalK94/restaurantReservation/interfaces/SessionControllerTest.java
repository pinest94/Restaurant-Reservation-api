package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.AuthenticationWrongException;
import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JWTUtil jwtUtil;

    @Test
    public void createWithValidAttributes() throws Exception {

        Long userId = 1004L;
        String email = "doingnow94@gmail.com";
        String password = "123456";
        String name = "hansol";

        User mockUser = User.builder().id(userId).email(email).name(name).password(password).build();
        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(userId, name, null)).willReturn("hansol.getJob");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"doingnow94@gmail.com\", \"password\" : \"123456\"" +
                        ", \"name\" : \"hansol\", " +
                        "\"phone\":\"010-1234-5678\", " +
                        "\"address\":\"경기 안양시 만안구 삼덕로 11번길 22\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"hansol.getJob\"}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq("doingnow94@gmail.com"), eq("123456"));
    }

    @Test
    public void createWithInValidAttributes() throws Exception {

        given(userService.authenticate("doingnow94@gmail.com", "1234567"))
                .willThrow(AuthenticationWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"doingnow94@gmail.com\", \"password\" : \"1234567\"" +
                        ", \"name\" : \"hansol\", " +
                        "\"phone\":\"010-1234-5678\", " +
                        "\"address\":\"경기 안양시 만안구 삼덕로 11번길 22\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("doingnow94@gmail.com"), eq("1234567"));
    }
}