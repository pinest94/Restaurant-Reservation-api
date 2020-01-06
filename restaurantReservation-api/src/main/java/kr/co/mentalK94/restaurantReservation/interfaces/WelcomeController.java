package kr.co.mentalK94.restaurantReservation.interfaces;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/api")
    public String hello2() {
        return "Hello, API";
    }
}
