package kr.co.mentalK94.restaurantReservation.domain;

public class UserExistedException extends RuntimeException{

    public UserExistedException(String email) {
        super("Email is already registered :" + email);
    }
}
