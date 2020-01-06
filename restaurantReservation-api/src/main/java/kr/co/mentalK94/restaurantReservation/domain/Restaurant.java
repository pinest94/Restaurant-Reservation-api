package kr.co.mentalK94.restaurantReservation.domain;

public class Restaurant {

    private final String name;
    private String address;
    private Long id;

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
}
