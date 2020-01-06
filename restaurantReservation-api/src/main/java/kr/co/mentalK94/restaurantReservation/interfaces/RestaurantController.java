package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.domain.MenuItem;
import kr.co.mentalK94.restaurantReservation.domain.MenuItemRepository;
import kr.co.mentalK94.restaurantReservation.domain.Restaurant;
import kr.co.mentalK94.restaurantReservation.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
//        List<Restaurant> restaurantList = new ArrayList<>();
//
//        restaurantList.add(new Restaurant(1001L, "Bob zip", "Seoul"));
//        restaurantList.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
//        restaurantList.add(new Restaurant(2023L, "Cutlet Food", "Seoul"));

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
//        List<Restaurant> restaurantList = new ArrayList<>();
//
//        restaurantList.add(new Restaurant(1001L, "Bob zip", "Seoul"));
//        restaurantList.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
//        restaurantList.add(new Restaurant(2023L, "Cutlet Food", "Seoul"));

        // 그런데 여기서 findAll로 모두 찾은 뒤 id값으로 해당 객체 찾는 역할을 여기서 하는 것은 옳지 않다.
        // 따라서 findAll이 아닌 findById(id)로 찾은 후 리턴해주어야 한다.
//        List<Restaurant> restaurantList = restaurantRepository.findAll();
        Restaurant restaurant = restaurantRepository.findById(id);

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantById(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }
}
