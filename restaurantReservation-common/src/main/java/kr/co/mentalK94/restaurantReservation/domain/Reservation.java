package kr.co.mentalK94.restaurantReservation.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 예약 Id

    @Column
    private Long userId; // 예약자 Id

    @Column
    private Long restaurantId; // 예약할 식당 Id

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
    @Min(1)
    private int partySize; // 인원 수

}
