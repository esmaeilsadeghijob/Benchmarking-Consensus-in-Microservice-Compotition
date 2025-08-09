package com.javatar.reviewsservice.controller;

import com.javatar.reviewsservice.entity.Review;
import com.javatar.reviewsservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class SagaReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // دریافت همه‌ی نقدها
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // دریافت نقد خاص با ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // ایجاد نقد جدید
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    // بروزرسانی نقد موجود
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Integer id, @RequestBody Review updatedReview) {
        Optional<Review> existing = reviewRepository.findById(id);
        if (existing.isPresent()) {
            updatedReview.setReviewid(id);
            return reviewRepository.save(updatedReview);
        } else {
            return null;
        }
    }

    // حذف نقد
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewRepository.deleteById(id);
    }
}
