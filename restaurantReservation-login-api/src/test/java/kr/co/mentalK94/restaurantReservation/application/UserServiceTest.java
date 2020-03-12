package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserExistedException;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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