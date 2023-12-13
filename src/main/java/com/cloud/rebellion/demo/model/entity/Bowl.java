package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.enums.Finish;
import com.cloud.rebellion.demo.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a bowl.
 * This class extends BaseEntity to inherit common properties and adds specific properties
 * unique to a bowl, such as bowl finish, for what type tobacco is the bowl
 * and the capacity, the bowl can have.
 */
@Getter
@Setter
@Entity
@Table
public class Bowl extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Finish finish;

    @Column(name = "for_type", nullable = false)
    private String forType;

    @Column(nullable = false)
    private int capacity;
}
