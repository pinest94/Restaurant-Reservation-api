package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String userId, String userPassword, String name, String email, String phone, String address) {
        User user = User.builder().userId(userId)
                .userPassword(userPassword)
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
        return userRepository.save(user);
    }
}
