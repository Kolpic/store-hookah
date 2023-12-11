package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a Hookah.
 * This class extends BaseEntity to inherit common properties and adds specific properties
 * unique to a Hookah, such as height and information.
 */
@Getter
@Setter
@Entity
@Table
public class Hookah extends BaseEntity {

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private String information;
}
