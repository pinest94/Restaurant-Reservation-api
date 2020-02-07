package kr.co.mentalK94.restaurantReservation.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @GeneratedValue
    @Id
    private Long id;

    @NotEmpty
    private String writer; // 작성자

    private double score; // 평점

    private String description; // 리뷰내용
}
