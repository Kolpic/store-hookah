package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.HMD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HMDRepository extends JpaRepository<HMD, Integer> {
}
