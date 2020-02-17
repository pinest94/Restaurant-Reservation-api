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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .name("박권용")
                .email("lavindain@gmail.com")
                .level(1)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("lavindain@gmail.com")));

    }


    @Test
    public void create() throws Exception {

        String email = "admin@restaurant.com";
        String name = "admin";

        User mockUser = User.builder().name(name).email(email).build();

        given(userService.addUser(name, email)).willReturn(mockUser);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"admin\", \"email\":\"admin@restaurant.com\"}"))
                .andExpect(status().isCreated());

        verify(userService).addUser(name, email);
    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/users/1001")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : 1001, \"name\" : \"admin\", \"email\":\"admin@restaurant.com\", \"level\":2}"))
                .andExpect(status().isOk());

        Long id = 1001L;
        String email = "admin@restaurant.com";
        String name = "admin";
        int level = 2;

        verify(userService).updateUser(eq(id), eq(name), eq(email) ,eq(level));
    }
}