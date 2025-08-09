package com.javatar.contentservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class TransactionPayload {
    private String reviewId;
    private Map<String, Object> data;

}

