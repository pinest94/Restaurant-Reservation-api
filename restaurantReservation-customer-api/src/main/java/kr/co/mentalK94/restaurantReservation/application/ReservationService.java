package kr.co.mentalK94.restaurantReservation.application;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {


    public void addReservation(Long restaurantId, Long userId, String name,
                               String date, String time, int partySize) {
        // TODO:
    }
}
