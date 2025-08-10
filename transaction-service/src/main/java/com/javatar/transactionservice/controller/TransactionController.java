package com.javatar.transactionservice.controller;

import com.javatar.transactionservice.model.Transaction;
import com.javatar.transactionservice.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction tx) {
        return service.save(tx);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return service.findAll();
    }
}

