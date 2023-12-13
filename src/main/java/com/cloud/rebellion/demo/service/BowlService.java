package com.cloud.rebellion.demo.service;

import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.NoSuchFieldTypeException;
import com.cloud.rebellion.demo.exception.bowl.NoSuchBowlException;
import com.cloud.rebellion.demo.mapper.BowlMapper;
import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.model.entity.Bowl;
import com.cloud.rebellion.demo.repository.BowlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class for managing bowl-related operations.
 * This class handles business logic for operations such as retrieving, creating, updating,
 * and deleting bowls. It interacts with the BowlRepository and uses BowlMapper for DTO conversions.
 */
@RequiredArgsConstructor
@Service
public class BowlService {

    private final BowlRepository bowlRepository;
    private final BowlMapper bowlMapper;

    /**
     * Retrieves all bowls.
     *
     * @return A list of BowlDTOs representing all bowls.
     */
    public List<BowlDTO> getAllBowls() {
        return bowlRepository
                .findAll()
                .stream()
                .map(bowlMapper::mapBowlToBowlDTO)
                .toList();
    }

    /**
     * Retrieves a bowl by its unique identifier.
     *
     * @param id The ID of the bowl to be retrieved.
     * @return A BowlDTO representing the bowl.
     * @throws NoSuchBowlException If the bowl is not found.
     */
    public BowlDTO getBowlById(int id) {
        return bowlRepository
                .findById(id)
                .map(bowlMapper::mapBowlToBowlDTO)
                .orElseThrow(() -> new NoSuchBowlException("There is no such bowl"));
    }

    /**
     * Retrieves a bowl by its name.
     *
     * @param name The name of the bowl to be retrieved.
     * @return A BowlDTO representing the bowl.
     * @throws NoSuchBowlException If no bowl is found with the given name.
     */
    public BowlDTO getBowlByName(String name) {
        return bowlRepository
                .findBowlByName(name)
                .map(bowlMapper::mapBowlToBowlDTO)
                .orElseThrow(() -> new NoSuchBowlException("There is no such bowl"));
    }

    /**
     * Creates a new bowl.
     *
     * @param providedBowl The BowlDTO containing the details of the bowl to be created.
     * @return A BowlDTO representing the newly created bowl.
     */
    public BowlDTO createNewBowl(BowlDTO providedBowl) {
        Bowl bowlToSave = bowlMapper.mapBowlDTOToBowl(providedBowl);
        bowlRepository.save(bowlToSave);
        return bowlMapper.mapBowlToBowlDTO(bowlToSave);
    }

    /**
     * Updates an existing bowl identified by the given ID with provided field values.
     *
     * @param id The ID of the bowl to be updated.
     * @param fields A map containing the field names and their new values.
     * @return A BowlDTO representing the updated bowl.
     * @throws NoSuchBowlException If the bowl to be updated is not found.
     * @throws EmptyPatchMapFieldsException If one or more provided fields are invalid.
     * @throws NoSuchFieldTypeException If the provided field type does not exist.
     */
    public BowlDTO updateExistingBowl(int id, Map<String, Object> fields) {
        Optional<Bowl> existingBowl = bowlRepository.findById(id);
        if (existingBowl.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Bowl.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    Object convertedValue = convertValue(field.getType(), value);
                    ReflectionUtils.setField(field, existingBowl.get(), convertedValue);
                } else {
                    throw new EmptyPatchMapFieldsException("One of the field/fields is empty or with wrong data type");
                }
            });
            bowlRepository.save(existingBowl.get());
            return bowlMapper.mapBowlToBowlDTO(existingBowl.get());
        } else {
            throw new NoSuchBowlException("The provided bowl what you want to modify is not present");
        }
    }

    // Helper method to convert field values
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


    /**
     * Deletes a bowl by its ID.
     *
     * @param id The ID of the bowl to be deleted.
     */
    public void deleteBowlById(int id) {
        bowlRepository.deleteById(id);
    }

    /**
     * Deletes a bowl by its name.
     *
     * @param name The name of the bowl to be deleted.
     * @throws NoSuchBowlException If the bowl to be deleted is not found.
     */
    public void deleteBowlByName(String name) {
        Optional<Bowl> bowlByName = bowlRepository.findBowlByName(name);
        if (bowlByName.isPresent()) {
            bowlRepository.delete(bowlByName.get());
        } else {
            throw new NoSuchBowlException("You want to delete non-existing bowl");
        }
    }
}
