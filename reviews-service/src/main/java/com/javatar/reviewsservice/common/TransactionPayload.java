package com.javatar.reviewsservice.common;

import lombok.Data;

import java.util.Map;

@Data
public class TransactionPayload {

    private Integer reviewId;
    private Map<String, Object> data;

    public TransactionPayload() {}

    public TransactionPayload(Integer reviewId, Map<String, Object> data) {
        this.reviewId = reviewId;
        this.data = data;
    }

}
