package kr.co.mentalK94.restaurantReservation.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.restaurantReservation.application.ReservationService;
import kr.co.mentalK94.restaurantReservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long restaurantId,
            @RequestBody Reservation resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

//        Long userId = claims.get("id", Long.class);
        Long userId = 1L;
        String name = claims.get("name", String.class);
        String date = resource.getDate();
        String time = resource.getTime();
        int partySize = resource.getPartySize();

        // addReservation 호출
        reservationService.addReservation(restaurantId, userId, name, date, time, partySize);

        String url = "/restaurants/"+restaurantId+"/reservations/1";
        return ResponseEntity.created(new URI(url)).body("예약 생성");
    }
}
