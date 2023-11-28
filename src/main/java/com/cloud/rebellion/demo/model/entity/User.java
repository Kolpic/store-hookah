package com.cloud.rebellion.demo.model.entity;

import com.cloud.rebellion.demo.model.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    private Account account;
}
