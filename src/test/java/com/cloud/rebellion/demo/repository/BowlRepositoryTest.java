package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.enums.Finish;
import com.cloud.rebellion.demo.model.entity.Bowl;
import com.cloud.rebellion.demo.model.entity.Hookah;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BowlRepositoryTest {

    @Autowired
    private BowlRepository bowlRepository;

    @BeforeEach
    void setUp() {
        Bowl bowl = new Bowl();

        bowl.setName("Name");
        bowl.setWeight(80);
        bowl.setBrand("Brand");
        bowl.setMaterial("Material");
        bowl.setOrigin("Origin");
        bowl.setColor(Color.RED);
        bowl.setPrice(80.09);
        bowl.setCapacity(20);
        bowl.setFinish(Finish.GLAZED);
        bowl.setForType("Yellow tobacco");
        bowlRepository.saveAndFlush(bowl);
    }

    @AfterEach
    void tearDown() {
        bowlRepository.deleteAll();
    }

    @Test
    public void testFindBowlByNameShouldFindTheBowlSuccessful() {
        // GIVEN --> created new Bowl in setUp method

        // WHEN
        Optional<Bowl> wantedBowl = bowlRepository.findBowlByName("Name");

        // THEN
        assertEquals("Name", wantedBowl.get().getName());
    }

    @Test
    public void testFindBowlByNameShouldNotFindTheBowlSuccessful() {
        // GIVEN --> created new Bowl in setUp method

        // WHEN
        Optional<Bowl> wantedBowl = bowlRepository.findBowlByName("Another Bowl");

        // THEN
        assertTrue(wantedBowl.isEmpty());
    }
}