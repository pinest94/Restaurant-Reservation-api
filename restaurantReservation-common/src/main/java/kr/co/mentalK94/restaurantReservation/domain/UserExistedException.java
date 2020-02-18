package kr.co.mentalK94.restaurantReservation.domain;

public class UserExistedException extends RuntimeException{

    public UserExistedException(String userId) {
        super("ID is already registered :" + userId);
    }
}
