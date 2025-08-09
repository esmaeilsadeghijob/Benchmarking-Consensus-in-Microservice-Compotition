package com.javatar.contentservice.service;

import com.javatar.contentservice.client.*;
import com.javatar.contentservice.common.TransactionPayload;
import org.springframework.stereotype.Service;

@Service
public class SagaService {

    private final CrdClient crd;
    private final GenreClient genre;
    private final LabelClient label;
    private final ReviewClient review;
    private final YearClient year;

    public SagaService(CrdClient crd, GenreClient genre, LabelClient label, ReviewClient review, YearClient year) {
        this.crd = crd;
        this.genre = genre;
        this.label = label;
        this.review = review;
        this.year = year;
    }

    public boolean executeSaga(TransactionPayload payload) {
        try {
            crd.create(payload);
            genre.create(payload);
            label.create(payload);
            review.create(payload);
            year.create(payload);
            return true;
        } catch (Exception e) {
            rollback(payload);
            return false;
        }
    }

    private void rollback(TransactionPayload payload) {
        year.compensate(payload);
        review.compensate(payload);
        label.compensate(payload);
        genre.compensate(payload);
        crd.compensate(payload);
    }
}

