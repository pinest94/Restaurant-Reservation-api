package kr.co.mentalK94.restaurantReservation.interfaces;

import lombok.Data;

@Data
public class SessionRequestDTO {

    private String userId;

    private String userPassword;
}
