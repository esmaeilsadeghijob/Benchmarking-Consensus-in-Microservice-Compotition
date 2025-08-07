package com.javatar.reviewsservice.repository;

import com.javatar.reviewsservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
