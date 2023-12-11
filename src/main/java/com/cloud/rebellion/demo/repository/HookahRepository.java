package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Hookah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Hookah entities.
 * This interface extends JpaRepository, providing standard methods for CRUD operations,
 * and includes custom queries for hookah-specific operations.
 */
@Repository
public interface HookahRepository extends JpaRepository<Hookah, Integer> {

    /**
     * Retrieves a hookah by its name.
     *
     * @param name The name of the hookah to find.
     * @return An Optional containing the found Hookah, or an empty Optional if no hookah is found.
     */
    Optional<Hookah> findHookahByName(String name);
}
