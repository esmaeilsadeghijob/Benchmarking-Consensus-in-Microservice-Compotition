package com.javatar.yearsservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "years")
public class Year {

    @Id
    private Integer reviewid;
    private Integer year;

    public Year() {}

    public Year( Integer reviewid, Integer year) {
        this.reviewid = reviewid;
        this.year = year;
    }
}
