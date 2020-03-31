package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.ReservationService;
import kr.co.mentalK94.restaurantReservation.domain.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjYsIm5hbWUiOiLquYDtlZzshpQifQ.52oAO9QUJmq_wQPpLzF-tp2wqdweRv7c3gReapmThsY";


        Reservation mockReservation = Reservation.builder().id(1L).build();
        given(reservationService.addReservation(any(), any(), any(), any(), any(), anyInt())).willReturn(mockReservation);

        mvc.perform(post("/restaurants/123/reservations")
                .header("Authorization","Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\" : \"2020-03-31\", \"time\":\"17:00\", " +
                        "\"partySize\":16}"))
                .andExpect(status().isCreated());

        Long userId = 6L;
        Long restaurantId = 123L;
        String name = "김한솔";
        String date = "2020-03-31";
        String time = "17:00";
        int partySize = 16;

        verify(reservationService).addReservation(eq(restaurantId), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }


}