package com.javatar.transactionservice.service;

import com.javatar.transactionservice.model.Transaction;
import com.javatar.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction save(Transaction tx) {
        return repository.save(tx);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }
}
