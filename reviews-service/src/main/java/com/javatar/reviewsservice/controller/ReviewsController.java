package com.javatar.reviewsservice.controller;

import com.javatar.reviewsservice.entity.Review;
import com.javatar.reviewsservice.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private final ReviewRepository reviewRepository;

    public ReviewsController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/{reviewid}")
    public Review getReviewById(@PathVariable Integer reviewid) {
        return reviewRepository.findById(reviewid).orElse(null);
    }
}

