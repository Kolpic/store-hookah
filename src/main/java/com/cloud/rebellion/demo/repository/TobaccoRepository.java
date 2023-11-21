package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.entity.Tobacco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TobaccoRepository extends JpaRepository<Tobacco, Integer> {
}
