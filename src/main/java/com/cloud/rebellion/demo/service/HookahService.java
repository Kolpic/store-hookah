package com.cloud.rebellion.demo.service;

import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.InvalidObjectFieldsException;
import com.cloud.rebellion.demo.exception.NoSuchFieldTypeException;
import com.cloud.rebellion.demo.exception.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.HookahMapper;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.BaseEntity;
import com.cloud.rebellion.demo.model.entity.Hookah;
import com.cloud.rebellion.demo.model.entity.Product;
import com.cloud.rebellion.demo.repository.HookahRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
                .orElseThrow(() -> new NoSuchHookahException("There is no such hookah"));
    }

    public HookahDTO getHookahByName(String name) {
        Optional<Hookah> foundHookahByName = hookahRepository.findHookahByName(name);
        if (foundHookahByName.isPresent()) {
            return hookahMapper.mapHookahToHookahDTO(foundHookahByName.get());
        } else {
            throw new NoSuchHookahException("The hookah that you want to get is not present");
        }
    }

    public HookahDTO createNewHookah(HookahDTO providedHookah) {
        Hookah hookahToSave = hookahMapper.mapHookahDTOToHookah(providedHookah);
        hookahRepository.save(hookahToSave);
        return hookahMapper.mapHookahToHookahDTO(hookahToSave);
    }

    /**
     * Update an existing resource --- entierly --- PUT
     * Update an existing resource --- partially --- PATCH
     * We are using PATCH. We are using reflection, because we don't know which
     * field or fields will be modified. With the help of map (String) key
     * is the hookah field and the map (Object) value is the value corresponding
     * to that field we want to update.
     */
    public HookahDTO updateExistingHookah(int id, Map<String, Object> fields) {
        Optional<Hookah> existingHookah = hookahRepository.findById(id);
        // Checking with the help of optional if the hookah is found
        if (existingHookah.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Hookah.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    Object convertedValue = convertValue(field.getType(), value);
                    ReflectionUtils.setField(field, existingHookah.get(), convertedValue);
                } else {
                    throw new EmptyPatchMapFieldsException("One of the field/fields is empty or with wrong data type");
                }
            });
            hookahRepository.save(existingHookah.get());
            return hookahMapper.mapHookahToHookahDTO(existingHookah.get());
        } else {
            throw new NoSuchHookahException("The provided hookah what you want to modify is not present");
        }
    }

    private Object convertValue(Class<?> targetType, Object value) {
        if (targetType == String.class) {
            return value.toString();
        } else if (targetType == double.class) {
            return Double.parseDouble(value.toString());
        } else if (targetType == int.class) {
            return Integer.parseInt(value.toString());
        } else if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, value.toString());
        } else {
            throw new NoSuchFieldTypeException("The provided field is not present in the Hookah class");
        }
    }

    public void deleteById(int id) {
        hookahRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        Optional<Hookah> hookahByName = hookahRepository.findHookahByName(name);
        if (hookahByName.isPresent()) {
            hookahRepository.delete(hookahByName.get());
        } else {
            throw new NoSuchHookahException("You want to delete non-existing hookah");
        }
    }
}
