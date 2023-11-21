package com.cloud.rebellion.demo.mapper;

import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Hookah;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HookahMapper {

    HookahDTO mapHookahToHookahDTO(Hookah hookah);
    Hookah mapHookahDTOToHookah(HookahDTO hookah);
}
