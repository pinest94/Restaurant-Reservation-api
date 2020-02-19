package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.SessionDTO;
import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionDTO> create(
        @RequestBody User resource
        ) throws URISyntaxException {

        String accessToken = "ACCESS_TOKEN";

        String userId = resource.getUserId();
        String userPassword = resource.getUserPassword();

        userService.authenticate(userId, userPassword);
        SessionDTO sessionDTO = SessionDTO.builder().accessToken(accessToken).build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionDTO);
    }
}
