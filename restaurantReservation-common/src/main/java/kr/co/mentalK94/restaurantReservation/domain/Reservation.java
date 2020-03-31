package kr.co.mentalK94.restaurantReservation.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reservation {

    @Id
    @GeneratedValue
    private Long id; // 예약 Id

    private Long userId; // 예약자 Id

    @Column
    private String name; // 예약자 이름

    @Column
    @NotEmpty
    private String date; // 날짜

    @Column
    @NotEmpty
    private String time; // 시간

    @Column
    @NotNull
    private int partySize; // 인원 수

}
