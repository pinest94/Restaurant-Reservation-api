package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserExistedException;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(String email, String password, String name, String phone, String address) {
        Optional<User> existedEmail = userRepository.findByEmail(email);

        if(existedEmail.isPresent()) {
            throw new UserExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder().email(email)
                .password(encodedPassword)
                .name(name)
                .phone(phone)
                .address(address)
                .level(1)
                .build();
        return userRepository.save(user);
    }
}
