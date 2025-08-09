package com.javatar.labelsservice.controller;


import com.javatar.labelsservice.common.TransactionPayload;
import com.javatar.labelsservice.entity.Label;
import com.javatar.labelsservice.repository.LabelRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaLabelController {

    private final LabelRepository repository;

    public SagaLabelController(LabelRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody TransactionPayload payload) {
        Label label = extractLabel(payload);
        repository.save(label);
    }

    @PostMapping("/compensate")
    public void compensate(@RequestBody TransactionPayload payload) {
        repository.deleteById(payload.getReviewId());
    }

    private Label extractLabel(TransactionPayload payload) {
        String name = (String) payload.getData().get("label");
        return new Label(payload.getReviewId(), name);
    }
}
