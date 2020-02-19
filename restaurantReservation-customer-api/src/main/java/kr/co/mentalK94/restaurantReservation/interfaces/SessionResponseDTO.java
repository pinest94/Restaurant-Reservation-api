package kr.co.mentalK94.restaurantReservation.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDTO {

    private String accessToken;

}
