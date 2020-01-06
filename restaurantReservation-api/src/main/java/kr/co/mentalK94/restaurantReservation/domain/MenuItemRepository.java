package kr.co.mentalK94.restaurantReservation.domain;

import org.springframework.stereotype.Component;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantById(Long id);
}
