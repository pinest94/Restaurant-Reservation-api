package kr.co.mentalK94.restaurantReservation.application;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDTO {

    private String accessToken;

}
