package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 1. User list
    @GetMapping("/users")
    public List<User> list() {

        return userService.getUsers();
    }

    // 2. User create
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        User user = userService.addUser(resource.getName(), resource.getEmail());

        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    // 3. User update
    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody User resource) {
        userService.updateUser(id, resource.getName(), resource.getEmail(), resource.getLevel());
        return "{}";
    }

    // 4. User delete -> level : 0
    @DeleteMapping("/users/{id}")
    public String deactivate(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "{}";
    }
}
