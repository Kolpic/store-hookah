package com.cloud.rebellion.demo.controller;

import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.service.HookahService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hookahs")
public class HookahController {

    private final HookahService hookahService;

    @GetMapping()
    public ResponseEntity<List<HookahDTO>> getAllHookahs() {
        return new ResponseEntity<>(hookahService.getAllHookahs(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<HookahDTO> getHookahById(@PathVariable int id) {
        return new ResponseEntity<>(hookahService.getHookahById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<HookahDTO> getHookahByName(@PathVariable String name) {
        return ResponseEntity.ok(hookahService.getHookahByName(name));
    }

    @PostMapping()
    public ResponseEntity<HookahDTO> createNewHookah(@RequestBody HookahDTO hookahDTO) {
        return new ResponseEntity<>(hookahService.createNewHookah(hookahDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HookahDTO> updateHookah(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<>(hookahService.updateExistingHookah(id, fields), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteHookahById(@PathVariable int id) {
        hookahService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-by-name/{name}")
    public ResponseEntity<Void> deleteHookahByName(@PathVariable String name) {
        hookahService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
