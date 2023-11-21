package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.entity.Hookah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HookahRepository extends JpaRepository<Hookah, Integer> {

}
