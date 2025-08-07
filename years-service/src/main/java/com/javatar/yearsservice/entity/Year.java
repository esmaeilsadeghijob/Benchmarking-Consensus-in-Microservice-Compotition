package com.javatar.yearsservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "years")
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reviewid;
    private Integer year;

    public Year() {}

    public Year(Long id, Integer reviewid, Integer year) {
        this.id = id;
        this.reviewid = reviewid;
        this.year = year;
    }
}
