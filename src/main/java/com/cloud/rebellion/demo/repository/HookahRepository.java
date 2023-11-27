package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Hookah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HookahRepository extends JpaRepository<Hookah, Integer> {

    Optional<Hookah> findHookahByName(String name);
}
