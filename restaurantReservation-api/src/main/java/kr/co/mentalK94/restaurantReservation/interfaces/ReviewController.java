package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.ReviewService;
import kr.co.mentalK94.restaurantReservation.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/restaurants/{id}/review")
    public ResponseEntity<?> create(@PathVariable("id") Long id) throws URISyntaxException {
        Review review = Review.builder().build();

        reviewService.addReview(review);

        URI location = new URI("/restaurants/1/review");
        return ResponseEntity.created(location).body("{}");
    }

}
