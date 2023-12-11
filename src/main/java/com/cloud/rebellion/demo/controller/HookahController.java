package com.cloud.rebellion.demo.controller;

import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.service.HookahService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * Controller for handling requests related to Hookah operations.
 * This controller provides endpoints for retrieving, creating, updating,
 * and deleting Hookah data.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/hookahs")
public class HookahController {

    private final HookahService hookahService;

    /**
     * Retrieves a list of all available hookahs.
     *
     * @return ResponseEntity containing a list of HookahDTO and the HTTP status.
     */
    @GetMapping()
    public ResponseEntity<List<HookahDTO>> getAllHookahs() {
        return new ResponseEntity<>(hookahService.getAllHookahs(), HttpStatus.OK);
    }

    /**
     * Retrieves a specific hookah by its unique identifier.
     *
     * @param id The unique identifier of the hookah.
     * @return ResponseEntity containing the requested HookahDTO and the HTTP status.
     */
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<HookahDTO> getHookahById(@PathVariable int id) {
        return new ResponseEntity<>(hookahService.getHookahById(id), HttpStatus.OK);
    }

    /**
     * Retrieves a specific hookah by its name.
     *
     * @param name The name of the hookah.
     * @return ResponseEntity containing the requested HookahDTO and the HTTP status.
     */
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<HookahDTO> getHookahByName(@PathVariable String name) {
        return ResponseEntity.ok(hookahService.getHookahByName(name));
    }

    /**
     * Creates a new hookah.
     *
     * @param hookahDTO The data transfer object containing hookah details.
     * @return ResponseEntity containing the created HookahDTO and the HTTP status.
     */
    @PostMapping()
    public ResponseEntity<HookahDTO> createNewHookah(@RequestBody HookahDTO hookahDTO) {
        return new ResponseEntity<>(hookahService.createNewHookah(hookahDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing hookah identified by the given ID.
     *
     * @param id The unique identifier of the hookah to be updated.
     * @param fields A map containing the fields to be updated.
     * @return ResponseEntity containing the updated HookahDTO and the HTTP status.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<HookahDTO> updateHookah(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(hookahService.updateExistingHookah(id, fields), HttpStatus.OK);
    }

    /**
     * Deletes a specific hookah by its unique identifier.
     *
     * @param id The unique identifier of the hookah to be deleted.
     * @return ResponseEntity with the HTTP status.
     */
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteHookahById(@PathVariable int id) {
        hookahService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a specific hookah by its name.
     *
     * @param name The name of the hookah to be deleted.
     * @return ResponseEntity with the HTTP status.
     */
    @DeleteMapping("/delete-by-name/{name}")
    public ResponseEntity<Void> deleteHookahByName(@PathVariable String name) {
        hookahService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
