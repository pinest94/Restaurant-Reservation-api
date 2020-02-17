package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(String name, String email) {
        User user = User.builder().name(name).email(email).build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String name, String email, int level) {
        User user = userRepository.findById(id).orElse(null);
        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);
        return user;
    }
}
