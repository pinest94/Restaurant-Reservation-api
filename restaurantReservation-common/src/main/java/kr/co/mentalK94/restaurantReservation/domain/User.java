package kr.co.mentalK94.restaurantReservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; // 아이디

    @Column
    private String password; // 비밀번호

    @NotEmpty
    @Column
    private String name; // 이름

    @Column
    private Long restaurantId; // 가게 id(가게 주인인 경우에 해당)

    @NotEmpty
    @Column
    private String email; // 이메일

    @Column
    private String phone; // 전화번호

    @Column
    private String address; // 주소

    @NotNull
    @Column
    private int level; // 회원 권한 (0: 권한없음 / 1: 일반사용자 / 2: 가게주인 / 3: 관리자)

    public boolean isAdmin() {
        return level>=3;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deActivate() {
        setLevel(0);
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
        this.level = 2;
}

    public boolean isRestaurantOwner() { // 가게 주인인지 확인
        return this.level == 2;
    }
}
