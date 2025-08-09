package com.javatar.artistsservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Artist {

    @Id
    private Integer reviewId;
    private String artistName;

}
