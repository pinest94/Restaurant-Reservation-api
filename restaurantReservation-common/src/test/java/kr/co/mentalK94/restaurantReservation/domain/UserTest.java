package kr.co.mentalK94.restaurantReservation.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void creation() {
        User user = User.builder()
                .name("김한솔")
                .email("doingnow94@gmail.com")
                .level(3)
                .build();

        assertThat(user.getName(), is("김한솔"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void restaurantOwner() {
        User user = User.builder().level(1).build();

        assertThat(user.isRestaurantOwner(), is(false));

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner(), is(true));
        assertThat(user.getRestaurantId(), is(1004L));
    }

}