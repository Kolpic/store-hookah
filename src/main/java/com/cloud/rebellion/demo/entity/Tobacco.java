package com.cloud.rebellion.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Tobacco extends BaseEntity {

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String flavour;

    @Column(nullable = false)
    private String type;
}
