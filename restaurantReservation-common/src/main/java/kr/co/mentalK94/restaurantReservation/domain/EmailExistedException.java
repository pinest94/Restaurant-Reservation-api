package kr.co.mentalK94.restaurantReservation.domain;

public class EmailExistedException extends RuntimeException{

    public EmailExistedException(String email) {
        super("Email is already registered :" + email);
    }

}

