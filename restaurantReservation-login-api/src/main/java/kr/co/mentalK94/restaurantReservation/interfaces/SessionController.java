package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.UserService;
import kr.co.mentalK94.restaurantReservation.domain.User;
import kr.co.mentalK94.restaurantReservation.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDTO> create(
        @RequestBody SessionRequestDTO resource
        ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);
        logger.info("userId : " + user.getId());
        logger.info("restaurantID : " + user.getRestaurantId());

        String accessToken = jwtUtil.createToken(user.getId(), user.getName(),
                                                user.isRestaurantOwner() ? user.getRestaurantId() : null);

        SessionResponseDTO sessionResponseDTO = SessionResponseDTO.builder().accessToken(accessToken).build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionResponseDTO);
    }
}
