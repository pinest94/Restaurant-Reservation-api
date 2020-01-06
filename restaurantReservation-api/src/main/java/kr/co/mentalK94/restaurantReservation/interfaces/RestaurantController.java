package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurantList = new ArrayList<>();

        Restaurant restaurant = new Restaurant(1001L, "Bob zip", "Seoul");
        restaurantList.add(restaurant);
        return restaurantList;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        List<Restaurant> restaurantList = new ArrayList<>();

        restaurantList.add(new Restaurant(1001L, "Bob zip", "Seoul"));
        restaurantList.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
        restaurantList.add(new Restaurant(2023L, "Cutlet Food", "Seoul"));

        return restaurantList.stream().filter(r->r.getId().equals(id)).findFirst().orElse(null);
    }
}
