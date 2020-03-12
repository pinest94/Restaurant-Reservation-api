package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.utils.JWTUtil;
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

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDTO> create(
        @RequestBody SessionRequestDTO resource
        ) throws URISyntaxException {

        String userId = resource.getUserId();
        String userPassword = resource.getUserPassword();
        //String name = resource.getName();

        User user = userService.authenticate(userId, userPassword);

        String accessToken = jwtUtil.createToken(user.getUserId(), user.getName());

        SessionResponseDTO sessionResponseDTO = SessionResponseDTO.builder().accessToken(accessToken).build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionResponseDTO);
    }
}
