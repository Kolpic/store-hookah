package com.cloud.rebellion.demo.repository;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.model.entity.Hookah;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Blob;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class HookahRepositoryTest {

    @Autowired
    private HookahRepository hookahRepository;

    @AfterEach
    void tearDown() {
        hookahRepository.deleteAll();
    }

    @Test
    public void testFindHookahByNameShouldFindTheHookahSuccessful() {
        // GIVEN
        saveNewHookahInTheDataBase();

        // WHEN
        Optional<Hookah> wantedHookah = hookahRepository.findHookahByName("Vyro One X Novitec");

        // THEN
        assertTrue(wantedHookah.isPresent());
        assertEquals("Vyro One X Novitec", wantedHookah.get().getName());
    }

    @Test
    public void testFindHookahByNameShouldNotFindTheHookahSuccessful() {
        // GIVEN
        saveNewHookahInTheDataBase();

        // WHEN
        Optional<Hookah> wantedHookah = hookahRepository.findHookahByName("Another Hookah");

        // THEN
        assertTrue(wantedHookah.isEmpty());
    }

    private void saveNewHookahInTheDataBase() {
        Hookah hookah = new Hookah();
        hookah.setId(1);
        hookah.setName("Vyro One X Novitec");
        hookah.setWeight(50);
        hookah.setBrand("Makloud");
        hookah.setOrigin("Russia");
        hookah.setMaterial("Steel");
        hookah.setColor(Color.RED);
        hookah.setPrice(50.33);
        hookah.setHeight(100);
        hookah.setInformation("More info");
        hookahRepository.saveAndFlush(hookah);
    }
}