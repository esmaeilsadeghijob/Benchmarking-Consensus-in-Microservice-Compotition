package com.javatar.crdservice.common;

import java.util.Map;

public class TransactionPayload {
    private String reviewId;
    private Map<String, Object> data;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

