package kr.co.mentalK94.restaurantReservation.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.mentalK94.restaurantReservation.application.ReservationService;
import kr.co.mentalK94.restaurantReservation.domain.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @GetMapping("/reservations")
    public List<Reservation> list(
            Authentication authentication
    ) {
        Claims claims = (Claims) authentication.getPrincipal();

        Long restaurantId = claims.get("restaurantId", Long.class);

        logger.info("restarant ID : "+restaurantId);

        List<Reservation> reservationList = reservationService.getReservations(restaurantId);
        return reservationList;
    }
}
