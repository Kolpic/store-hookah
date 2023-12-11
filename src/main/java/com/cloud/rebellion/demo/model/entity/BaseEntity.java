package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.enums.Color;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

/**
 * Base class for the individual parts of the hookah. The idea is to reduce code repeat.
 * Instead of this code to be present in every class(hookah, HMD, Accessory etc) it can be placed in
 * one BaseEntity class and other classes to extend BaseEntity
 */
@Getter
@Setter
// https://vladmihalcea.com/how-to-inherit-properties-from-a-base-class-entity-using-mappedsuperclass-with-jpa-and-hibernate/
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(nullable = false)
    private double price;

    @Column(name = "image_binary")
    private Blob image_binary;
}
