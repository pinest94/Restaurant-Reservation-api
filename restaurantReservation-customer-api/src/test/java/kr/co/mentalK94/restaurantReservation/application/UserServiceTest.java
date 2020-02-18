package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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
}