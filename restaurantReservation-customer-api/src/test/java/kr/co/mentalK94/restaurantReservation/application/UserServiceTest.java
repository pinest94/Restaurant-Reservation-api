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
                .email("doingnow94@gmail.com")
                .password("123456")
                .name("hansol")
                .phone("010-3920-7847")
                .address("경기 안양시 만안구 삼덕로 11번길 22")
                .build();

        userService.addUser("doingnow94@gmail.com", "123456",
                "hansol", "010-3920-7847",
                "경기 안양시 만안구 삼덕로 11번길 22");

        verify(userRepository).save(any());
    }

    @Test
    public void addUserWithExistedIdTest() {

        String email = "doingnow94@gmail.com";

        User mockUser = User.builder()
                .email("doingnow94@gmail.com")
                .password("123456")
                .name("hansol")
                .phone("010-3920-7847")
                .address("경기 안양시 만안구 삼덕로 11번길 22")
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        Exception exception = assertThrows(
                UserExistedException.class,
                () -> userService.addUser("doingnow94@gmail.com", "123456",
                        "hansol", "010-3920-7847",
                        "경기 안양시 만안구 삼덕로 11번길 22"));

        verify(userRepository, never()).save(any());
    }

}