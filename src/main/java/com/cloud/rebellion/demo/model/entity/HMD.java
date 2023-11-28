package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class HMD extends BaseEntity {

    @Column(nullable = false)
    private String type;
}
