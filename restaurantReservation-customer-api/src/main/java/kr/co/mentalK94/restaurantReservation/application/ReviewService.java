package kr.co.mentalK94.restaurantReservation.application;

import kr.co.mentalK94.restaurantReservation.domain.Review;
import kr.co.mentalK94.restaurantReservation.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name,
                            double score, String description) {

        Review review = Review.builder()
                        .restaurantId(restaurantId)
                        .writer(name)
                        .score(score)
                        .description(description)
                        .build();

        // TODO: ID -> generateValue로 만들어야함
        // review.setId(1L);
        return reviewRepository.save(review);
    }
}
