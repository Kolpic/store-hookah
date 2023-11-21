package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Bowl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BowlRepository extends JpaRepository<Bowl, Integer> {
}
