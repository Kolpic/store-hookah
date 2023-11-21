package com.cloud.rebellion.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id", nullable = false)
    private int itemId;

    @Column(nullable = false)
    private int quantity;
}
