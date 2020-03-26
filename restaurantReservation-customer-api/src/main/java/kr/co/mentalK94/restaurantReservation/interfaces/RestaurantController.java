package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.RestaurantService;
import kr.co.mentalK94.restaurantReservation.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/allRestaurants")
    public List<Restaurant> allList() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list(@RequestParam("region") String region, @RequestParam("category") Long categoryId) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        // 기본정보 + 메뉴정보
        Restaurant restaurant = restaurantService.getRestaurantById(id);

        return restaurant;
    }
}
