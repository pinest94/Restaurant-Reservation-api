package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.EmailExistedException;
import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserExistedException;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.h2.security.SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String userId, String userPassword, String name, String email, String phone, String address) {
        Optional<User> existedUserId = userRepository.findByUserId(userId);
        Optional<User> existedEmail = userRepository.findByEmail(email);
        if(existedUserId.isPresent()) {
            throw new UserExistedException(userId);
        }

        if(existedEmail.isPresent()) {
            throw new EmailExistedException(email);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPassword);

        User user = User.builder().userId(userId)
                .userPassword(encodedPassword)
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .level(1)
                .build();
        return userRepository.save(user);
    }
}
