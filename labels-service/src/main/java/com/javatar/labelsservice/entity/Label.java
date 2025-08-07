package com.javatar.labelsservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "labels")
public class Label {

    @Id
    private Integer reviewid;
    private String label;

    public Label() {}

    public Label( Integer reviewid, String label) {
        this.reviewid = reviewid;
        this.label = label;
    }

}
