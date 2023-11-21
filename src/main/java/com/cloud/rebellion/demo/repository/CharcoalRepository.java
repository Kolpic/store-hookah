package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Charcoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharcoalRepository extends JpaRepository<Charcoal, Integer> {
}
