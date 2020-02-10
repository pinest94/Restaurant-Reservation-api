package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.*;
import kr.co.mentalK94.restaurantReservation.domain.MenuItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(2020L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(2020L)).willReturn(java.util.Optional.of(restaurant));
    }

    @Test
    public void getRestaurantWithExistedTest() {
        Restaurant restaurant = restaurantService.getRestaurantById(2020L);

        assertThat(restaurant.getId(), is(2020L));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExitedTest() {
        Restaurant restaurant = restaurantService.getRestaurantById(404L);
    }



    @Test
    public void getRestaurantsTest() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(2020L));
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder().name("Bukyung").address("Gunpo").build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
        assertThat(created.getName(), is("Bukyung"));
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(1004L, "cafe", "Busan");
        assertThat(restaurant.getName(), is(updatedRestaurant.getName()));
        assertThat(restaurant.getAddress(), is(updatedRestaurant.getAddress()));
    }
}