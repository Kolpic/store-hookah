package com.cloud.rebellion.demo.mapper;

import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.model.entity.Bowl;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper interface for converting between Bowl entity and BowlDTO.
 * This mapper provides methods for converting Bowl entities to their Data Transfer Object (DTO) representation
 * and vice versa, facilitating the separation of database entity model and the model exposed to the user.
 */
@Mapper(componentModel = "spring")
public interface BowlMapper {

    /**
     * Converts a Bowl entity to a BowlDTO.
     *
     * @param bowl The Bowl entity to be converted.
     * @return The corresponding BowlDTO.
     */
    BowlDTO mapBowlToBowlDTO(Bowl bowl);

    /**
     * Converts a BowlDTO to a Bowl entity.
     *
     * @param bowlDTO The BowlDTO to be converted.
     * @return The corresponding Bowl entity.
     */
    Bowl mapBowlDTOToBowl(BowlDTO bowlDTO);
}
