package com.javatar.labelsservice.controller;

import com.javatar.labelsservice.entity.Label;
import com.javatar.labelsservice.repository.LabelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labels")
public class LabelsController {

    private final LabelRepository labelRepository;

    public LabelsController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @GetMapping("/{reviewid}")
    public List<Label> getLabelsByReviewId(@PathVariable Integer reviewid) {
        return labelRepository.findByReviewid(reviewid);
    }
}
