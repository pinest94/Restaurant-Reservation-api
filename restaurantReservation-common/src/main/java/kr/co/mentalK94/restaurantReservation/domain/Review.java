package kr.co.mentalK94.restaurantReservation.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private Long restaurantId; // 레스토랑 ID

    @Column
    private String writer; // 작성자

    @NotNull
    @Column
    private double score; // 평점

    @NotEmpty
    @Column
    private String description; // 리뷰내용

}
