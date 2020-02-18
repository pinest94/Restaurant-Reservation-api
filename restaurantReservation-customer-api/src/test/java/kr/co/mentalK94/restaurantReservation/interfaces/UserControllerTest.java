package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;


    // UserId, UserPassword, name, email, phone, address, level

    @Test
    public void create() throws Exception {

        User mockUser = User.builder()
                .id(1001L)
                .userId("rlagksthf209")
                .userPassword("123456")
                .name("hansol")
                .email("doingnow94@gmail.com")
                .address("경기 안양시 만안구 삼덕로 11번길 22")
                .phone("010-1234-5678")
                .build();

        given(userService.addUser("rlagksthf209",
                "123456",
                "hansol",
                "doingnow94@gmail.com",
                "010-1234-5678",
                "경기 안양시 만안구 삼덕로 11번길 22"))
                .willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\" : \"rlagksthf209\", \"userPassword\" : \"123456\"" +
                        ", \"name\" : \"hansol\", \"email\":\"doingnow94@gmail.com\", " +
                        "\"phone\":\"010-1234-5678\", " +
                        "\"address\":\"경기 안양시 만안구 삼덕로 11번길 22\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1001"));

        verify(userService).addUser(eq("rlagksthf209"), eq("123456"),
                eq("hansol"), eq("doingnow94@gmail.com"), eq("010-1234-5678")
                ,eq("경기 안양시 만안구 삼덕로 11번길 22"));

    }
}