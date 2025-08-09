package com.javatar.genresservice.common;

import lombok.Data;

import java.util.Map;

@Data
public class TransactionPayload {
    private String reviewId;
    private Map<String, Object> data;

}

