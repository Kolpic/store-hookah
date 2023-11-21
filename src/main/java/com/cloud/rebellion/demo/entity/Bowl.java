package com.cloud.rebellion.demo.entity;

import com.cloud.rebellion.demo.enums.Finish;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
