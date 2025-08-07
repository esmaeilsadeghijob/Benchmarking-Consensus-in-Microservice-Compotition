package com.javatar.reviewsservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    private Integer reviewid;

    private String title;
    private String artist;
    private String url;
    private Double score;
    private Integer bestNewMusic;
    private String author;
    private String authorType;
    private String pubDate;
    private Integer pubWeek;
    private Integer pubWeekday;
    private Integer pubDay;
    private Integer pubMonth;
    private Integer pubYear;
}

