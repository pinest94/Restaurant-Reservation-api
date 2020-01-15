package kr.co.mentalK94.restaurantReservation.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    List<Restaurant> restaurantList = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        restaurantList.add(new Restaurant(1001L, "Bob zip", "Seoul"));
        restaurantList.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
        restaurantList.add(new Restaurant(2023L, "Cutlet Food", "Seoul"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantList;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantList.stream().filter(r->r.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234L);
        restaurantList.add(restaurant);
        return restaurant;
    }
}
