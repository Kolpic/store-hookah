package com.cloud.rebellion.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String address;

    @Column(name = "credit_card_info", nullable = false)
    private String creditCardInfo;

    @Column(name = "shipping_info", nullable = false)
    private String shippingInfo;

    @OneToOne(targetEntity = ShoppingCart.class)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
}
