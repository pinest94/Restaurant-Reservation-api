package kr.co.mentalK94.restaurantReservation.domain;

import ch.qos.logback.core.BasicStatusManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final String name;
    private String address;
    private Long id;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public String getAddress() {
        return this.address;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }
}
