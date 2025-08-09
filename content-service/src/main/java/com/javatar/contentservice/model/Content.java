package com.javatar.contentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Content {
    @Id
    private String reviewid;
    private String content;
}
