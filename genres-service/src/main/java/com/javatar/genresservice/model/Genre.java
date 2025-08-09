package com.javatar.genresservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private Integer reviewid;
    private String genre;

    public Genre(Integer reviewId, String name) {
        this.reviewid = reviewId;
        this.genre = name;
    }
}
