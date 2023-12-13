package com.cloud.rebellion.demo.controller;

import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.service.BowlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing bowls.
 * This controller provides endpoints for operations such as retrieving, creating, updating,
 * and deleting bowls. It uses BowlService for business logic and interacts with clients via BowlDTOs.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/bowls")
public class BowlController {

    private final BowlService bowlService;

    /**
     * Retrieves a list of all bowls.
     *
     * @return ResponseEntity containing a list of BowlDTOs and the HTTP status OK.
     */
    @GetMapping()
    public ResponseEntity<List<BowlDTO>> getAllBowls() {
        return new ResponseEntity<>(bowlService.getAllBowls(), HttpStatus.OK);
    }

    /**
     * Retrieves a specific bowl by its unique identifier.
     *
     * @param id The unique identifier of the bowl.
     * @return ResponseEntity containing the requested BowlDTO and the HTTP status OK.
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<BowlDTO> getBowlById(@PathVariable int id) {
        return new ResponseEntity<>(bowlService.getBowlById(id), HttpStatus.OK);
    }

    /**
     * Retrieves a specific bowl by its name.
     *
     * @param name The name of the bowl.
     * @return ResponseEntity containing the requested BowlDTO and the HTTP status OK.
     */
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<BowlDTO> getBowlByName(@PathVariable String name) {
        return new ResponseEntity<>(bowlService.getBowlByName(name), HttpStatus.OK);
    }

    /**
     * Creates a new bowl.
     *
     * @param bowlDTO The BowlDTO containing the details of the new bowl.
     * @return ResponseEntity containing the created BowlDTO and the HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<BowlDTO> createNewBowl(@RequestBody BowlDTO bowlDTO) {
        return new ResponseEntity<>(bowlService.createNewBowl(bowlDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing bowl identified by the given ID.
     *
     * @param id The unique identifier of the bowl to be updated.
     * @param fields A map containing the fields to be updated.
     * @return ResponseEntity containing the updated BowlDTO and the HTTP status OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<BowlDTO> updateBowl(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(bowlService.updateExistingBowl(id, fields), HttpStatus.OK);
    }

    /**
     * Deletes a specific bowl by its unique identifier.
     *
     * @param id The unique identifier of the bowl to be deleted.
     * @return ResponseEntity with the HTTP status NO_CONTENT.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteBowlById(@PathVariable int id) {
        bowlService.deleteBowlById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a specific bowl by its name.
     *
     * @param name The name of the bowl to be deleted.
     * @return ResponseEntity with the HTTP status NO_CONTENT.
     */
    @DeleteMapping("/delete-by-name/{name}")
    public ResponseEntity<Void> deleteBowlByName(@PathVariable String name) {
        bowlService.deleteBowlByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
