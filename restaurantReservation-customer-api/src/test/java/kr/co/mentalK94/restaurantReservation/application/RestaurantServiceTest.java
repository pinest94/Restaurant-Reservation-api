package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .writer("sungjae")
                .score(2.5)
                .description("너무 맛없어...")
                .build());

        given(reviewRepository.findAllByRestaurantId(2020L)).willReturn(reviews);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());
        given(menuItemRepository.findAllByRestaurantId(2020L)).willReturn(menuItems);
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

        verify(menuItemRepository).findAllByRestaurantId(eq(2020L));
        verify(reviewRepository).findAllByRestaurantId(eq(2020L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        Review review = restaurant.getReviews().get(0);

        assertThat(review.getWriter(), is("sungjae"));
        assertThat(review.getScore(), is(2.5));
        assertThat(restaurant.getId(), is(2020L));
        assertThat(menuItem.getName(), is("Kimchi"));
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