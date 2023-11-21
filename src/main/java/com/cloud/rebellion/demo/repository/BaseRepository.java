package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends JpaRepository<Base, Integer> {
}
