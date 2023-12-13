package com.cloud.rebellion.demo.model.dto;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.enums.Finish;
import com.cloud.rebellion.demo.helper.BlobDeserializer;
import com.cloud.rebellion.demo.helper.BlobSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

/**
 * Data Transfer Object (DTO) for Bowl.
 * This class represents the data structure for transferring Bowl data between processes.
 * It contains various attributes of a Bowl, such as name, weight, brand, origin, etc.
 */
@Getter
@Setter
public class BowlDTO {

    private String name;
    private int weight;
    private String brand;
    private String origin;
    private String material;
    private Color color;
    private double price;
    /**
     * The binary representation of the hookah's image.
     * This field is serialized and deserialized using custom BlobSerializer and BlobDeserializer.
     */
    @JsonSerialize(using = BlobSerializer.class)
    @JsonDeserialize(using = BlobDeserializer.class)
    private Blob image_binary;
    private Finish finish;
    private String forType;
    private int capacity;
}
