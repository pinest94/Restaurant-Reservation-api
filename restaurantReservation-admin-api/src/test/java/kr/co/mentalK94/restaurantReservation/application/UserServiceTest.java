package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers() {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().name("hansol kim").email("aaa@gmail.com").level(1).build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        assertThat(users.get(0).getEmail(), is("aaa@gmail.com"));
    }

    @Test
    public void addUser() {

        String email = "admin@restaurant.com";
        String name = "admin";

        User mockUser = User.builder().name(name).email(email).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(name, email);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser() {
        Long id = 1001L;
        String email = "hansol@restaurant.com";
        String name = "hansol";
        int level = 3;

        User mockUser = User.builder().id(id).name("admin").email(email).level(1).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, name, email, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is("hansol"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void deleteUser() {

        Long id = 1001L;
        int level = 3;

        User mockUser = User.builder().id(id).level(level).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deleteUser(id);

        verify(userRepository).findById(eq(id));

        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(false));
    }
}