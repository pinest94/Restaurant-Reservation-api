package kr.co.mentalK94.restaurantReservation.application;

public class AuthenticationWrongException extends RuntimeException{

    AuthenticationWrongException() {
        super("ID or Password is wrong!");
    }
}
