package kr.co.mentalK94.restaurantReservation.interfaces;

import lombok.Data;

@Data
public class SessionRequestDTO {

    private String email;

    private String password;

    private String name;

    private Long userId;
}
