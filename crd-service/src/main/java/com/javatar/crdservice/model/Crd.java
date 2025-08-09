package com.javatar.crdservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Crd {

    @Id
    private String id;
    private String value;

    public Crd() {}

    public Crd(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
