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

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User authenticate(String userId, String userPassword) {

        // TODO: 개발 필요
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new AuthenticationWrongException());

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new AuthenticationWrongException();
        }

        return user;
    }
}
