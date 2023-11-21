package com.cloud.rebellion.demo.service;

import com.cloud.rebellion.demo.exception.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.HookahMapper;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Hookah;
import com.cloud.rebellion.demo.repository.HookahRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HookahService {

    private final HookahRepository hookahRepository;
    private final HookahMapper hookahMapper;

    public List<HookahDTO> getAllHookahs() {
        return hookahRepository
                .findAll()
                .stream()
                .map(hookahMapper::mapHookahToHookahDTO)
                .toList();
    }

    public HookahDTO getHookahById(int id) {
        return hookahRepository
                .findById(id)
                .map(hookahMapper::mapHookahToHookahDTO)
                .orElseThrow(NoSuchHookahException::new);
    }
}
