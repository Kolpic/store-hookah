package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {
}
