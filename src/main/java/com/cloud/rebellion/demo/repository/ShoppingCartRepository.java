package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
