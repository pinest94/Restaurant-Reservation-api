package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.Reservation;
import kr.co.mentalK94.restaurantReservation.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getReservations() {
        Long restaurantId = 1004L;
        List<Reservation> reservations =  reservationService.getReservations(restaurantId);

        verify(reservationRepository).findAllByRestaurantId(restaurantId);
    }
}