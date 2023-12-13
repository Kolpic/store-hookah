package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.model.entity.Bowl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Bowl entities.
 * This interface extends JpaRepository to provide standard CRUD operations for Bowl entities
 * and includes a custom query method for finding a Bowl by its name.
 */
@Repository
public interface BowlRepository extends JpaRepository<Bowl, Integer> {

    /**
     * Retrieves a Bowl by its name.
     *
     * @param name The name of the Bowl to be retrieved.
     * @return An Optional containing the Bowl if found, or an empty Optional if no Bowl is found with the given name.
     */
    Optional<Bowl> findBowlByName(String name);
}
