package kr.co.mentalK94.restaurantReservation.domain;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
