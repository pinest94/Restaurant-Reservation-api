package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.*;
import kr.co.mentalK94.restaurantReservation.domain.MenuItem;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Before
    public void setUp() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurantTest() {
        Restaurant restaurant = restaurantService.getRestaurantById(2020L);
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(restaurant.getId(), is(2020L));
        assertThat(menuItem.getName(), is("Kimchi"));
    }

    @Test
    public void getRestaurantsTest() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1001L));
    }
}