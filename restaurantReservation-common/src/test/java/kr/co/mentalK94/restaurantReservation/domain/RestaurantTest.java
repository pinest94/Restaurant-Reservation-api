package kr.co.mentalK94.restaurantReservation.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTest {

    @Test
    public void creationTest() {
        Restaurant restaurant = Restaurant.builder()
                .id(1001L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertThat(restaurant.getId(), is(1001L));
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void informationTest() {
        Restaurant restaurant = Restaurant.builder()
                .id(1001L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }
}