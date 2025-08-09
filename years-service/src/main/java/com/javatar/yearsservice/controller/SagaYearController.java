package com.javatar.yearsservice.controller;


import com.javatar.yearsservice.common.TransactionPayload;
import com.javatar.yearsservice.entity.Year;
import com.javatar.yearsservice.repository.YearRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaYearController {

    private final YearRepository repository;

    public SagaYearController(YearRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody TransactionPayload payload) {
        Year year = extractYear(payload);
        repository.save(year);
    }

    @PostMapping("/compensate")
    public void compensate(@RequestBody TransactionPayload payload) {
        repository.deleteById(payload.getReviewId());
    }

    private Year extractYear(TransactionPayload payload) {
        Integer value = (Integer) payload.getData().get("year");
        return new Year(payload.getReviewId(), value);
    }
}
