package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.model.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private List<Product> products;

}
