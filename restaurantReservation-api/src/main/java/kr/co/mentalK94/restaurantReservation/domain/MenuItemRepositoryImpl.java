package kr.co.mentalK94.restaurantReservation.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    @Override
    public List<MenuItem> findAllByRestaurantById(Long id) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
        return menuItems;
    }
}
