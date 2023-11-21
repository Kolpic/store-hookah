package com.cloud.rebellion.demo.model.dto;

import com.cloud.rebellion.demo.enums.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HookahDTO {

    private String name;
    private int weight;
    private String brand;
    private String origin;
    private String material;
    private Color color;
    private double price;
    private int height;
    private String information;
}
