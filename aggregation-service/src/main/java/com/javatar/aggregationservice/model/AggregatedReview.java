package com.javatar.aggregationservice.model;

import lombok.Data;

import java.util.List;

@Data
public class AggregatedReview {
    private Integer reviewid;
    private List<String> genres;
    private List<String> labels;
    private List<Integer> years;
}
