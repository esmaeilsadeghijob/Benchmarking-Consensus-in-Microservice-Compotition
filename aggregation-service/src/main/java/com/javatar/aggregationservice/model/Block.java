package com.javatar.aggregationservice.model;


import lombok.Data;

import java.util.List;

@Data
public class Block {
    private String id;
    private List<String> data;
    private long timestamp;

}
