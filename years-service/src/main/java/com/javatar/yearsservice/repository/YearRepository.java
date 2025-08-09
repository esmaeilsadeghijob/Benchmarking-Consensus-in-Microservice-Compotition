package com.javatar.yearsservice.repository;

import com.javatar.yearsservice.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearRepository extends JpaRepository<Year, Integer> {
    List<Year> findByReviewid(Integer reviewid);
}
