package kr.co.mentalK94.restaurantReservation.interfaces;

import kr.co.mentalK94.restaurantReservation.application.ReviewService;
import kr.co.mentalK94.restaurantReservation.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{id}/reviews")
    public ResponseEntity<?> create(@PathVariable("id") Long id,
                                    @Valid @RequestBody Review resource)
                                    throws URISyntaxException {

        Review review = reviewService.addReview(resource);

        String url = "/restaurants/"+id+"/reviews/" + review.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
