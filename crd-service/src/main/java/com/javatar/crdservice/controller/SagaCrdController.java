package com.javatar.crdservice.controller;

import com.javatar.crdservice.common.TransactionPayload;
import com.javatar.crdservice.model.Crd;
import com.javatar.crdservice.repository.CrdRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaCrdController {

    private final CrdRepository repository;

    public SagaCrdController(CrdRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public void create(@RequestBody TransactionPayload payload) {
        Crd crd = extractCrd(payload);
        repository.save(crd);
    }

    @PostMapping("/compensate")
    public void compensate(@RequestBody TransactionPayload payload) {
        repository.deleteById(payload.getReviewId());
    }

    private Crd extractCrd(TransactionPayload payload) {
        String value = (String) payload.getData().get("crd");
        return new Crd(payload.getReviewId(), value);
    }
}

