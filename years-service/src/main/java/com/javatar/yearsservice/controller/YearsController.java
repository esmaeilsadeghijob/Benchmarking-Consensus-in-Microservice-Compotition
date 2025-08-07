package com.javatar.yearsservice.controller;

import com.javatar.yearsservice.entity.Year;
import com.javatar.yearsservice.repository.YearRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/years")
public class YearsController {

    private final YearRepository yearRepository;

    public YearsController(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    @GetMapping("/{reviewid}")
    public List<Year> getYearsByReviewId(@PathVariable Integer reviewid) {
        return yearRepository.findByReviewid(reviewid);
    }
}

