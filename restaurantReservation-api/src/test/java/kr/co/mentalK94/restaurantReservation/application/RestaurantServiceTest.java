package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.Restaurant;
import kr.co.mentalK94.restaurantReservation.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Before
    public void setUp() {
        // restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    public void getRestaurantTest() {
        Restaurant restaurant = restaurantService.getRestaurantById(2002L);

        assertThat(restaurant.getId(), is(2002L));
    }

}