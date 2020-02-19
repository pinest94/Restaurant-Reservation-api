package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserExistedException;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void addUserTest() {
        User user = User.builder()
                .userId("rlagksthf209")
                .userPassword("123456")
                .name("hansol")
                .email("doingnow94@gmail.com")
                .phone("010-3920-7847")
                .address("경기 안양시 만안구 삼덕로 11번길 22")
                .build();

        userService.addUser("rlagksthf209", "123456",
                "hansol", "doingnow94@gmail.com", "010-3920-7847",
                "경기 안양시 만안구 삼덕로 11번길 22");

        verify(userRepository).save(any());
    }

    @Test
    public void addUserWithExistedIdTest() {

        String id = "rlagksthf209";

        User mockUser = User.builder()
                .userId("rlagksthf209")
                .userPassword("123456")
                .name("hansol")
                .email("doingnow94@gmail.com")
                .phone("010-3920-7847")
                .address("경기 안양시 만안구 삼덕로 11번길 22")
                .build();

        given(userRepository.findByUserId(id)).willReturn(Optional.of(mockUser));

        Exception exception = assertThrows(
                UserExistedException.class,
                () -> userService.addUser("rlagksthf209", "123456",
                        "hansol", "doingnow94@gmail.com", "010-3920-7847",
                        "경기 안양시 만안구 삼덕로 11번길 22"));

        verify(userRepository, never()).save(any());
    }

    @Test
    public void authenticateWithInValidAttributes() {

        String userId = "rlagksthf";
        String userPassword = "123456";

        given(userRepository.findByUserId(userId)).willReturn(Optional.empty());

        Exception exception = assertThrows(
                AuthenticationWrongException.class,
                () -> userService.authenticate(userId, userPassword));
    }

    @Test
    public void authenticateWithValidAttributes() {

        String userId = "rlagksthf209";
        String userPassword = "123456";

        User mockUser = User.builder().userId(userId).build();
        given(userRepository.findByUserId(userId)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(userId, userPassword);

        assertThat(user.getUserId(), is(userId));

    }
}