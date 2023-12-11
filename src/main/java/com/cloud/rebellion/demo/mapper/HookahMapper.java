package com.cloud.rebellion.demo.mapper;

import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Hookah;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper interface for converting between Hookah entity and HookahDTO.
 * This mapper provides methods for converting Hookah entities to their Data Transfer Object (DTO) representation
 * and vice versa.
 */
@Mapper(componentModel = "spring")
public interface HookahMapper {

    /**
     * Maps a Hookah entity to a HookahDTO.
     *
     * @param hookah The Hookah entity to be mapped.
     * @return The mapped HookahDTO.
     */
    HookahDTO mapHookahToHookahDTO(Hookah hookah);

    /**
     * Maps a HookahDTO to a Hookah entity.
     *
     * @param HookahDTO The HookahDTO to be mapped.
     * @return The mapped Hookah entity.
     */
    Hookah mapHookahDTOToHookah(HookahDTO hookah);
}
