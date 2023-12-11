package com.cloud.rebellion.demo.mapper;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.helper.BlobDeserializer;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Hookah;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.sql.Blob;

import static org.junit.jupiter.api.Assertions.*;

class HookahMapperTest {

    private HookahMapper hookahMapper = Mappers.getMapper(HookahMapper.class);
    private Hookah hookah = new Hookah();
    private HookahDTO hookahDTO = new HookahDTO();

    @Test
    public void testMapHookahToHookahDTOShouldMapSuccessful() {
        // GIVEN

        hookah.setName("Name");
        hookah.setWeight(80);
        hookah.setBrand("Brand");
        hookah.setOrigin("Origin");
        hookah.setMaterial("Material");
        hookah.setColor(Color.RED);
        hookah.setPrice(80.00);
        hookah.setHeight(100);
        hookah.setInformation("More Info");

        // WHEN
        HookahDTO mappedHookahDTO = hookahMapper.mapHookahToHookahDTO(hookah);

        // THEN
        assertEquals(mappedHookahDTO.getName(), hookah.getName());
        assertEquals(mappedHookahDTO.getWeight(), hookah.getWeight());
        assertEquals(mappedHookahDTO.getBrand(), hookah.getBrand());
        assertEquals(mappedHookahDTO.getOrigin(), hookah.getOrigin());
        assertEquals(mappedHookahDTO.getMaterial(), hookah.getMaterial());
        assertEquals(mappedHookahDTO.getColor(), hookah.getColor());
        assertEquals(mappedHookahDTO.getPrice(), hookah.getPrice());
        assertEquals(mappedHookahDTO.getHeight(), hookah.getHeight());
        assertEquals(mappedHookahDTO.getInformation(), hookah.getInformation());
    }

    @Test
    public void testMapHookahToHookahDTOShouldReturnNullPointerIfTheHookahIsNull() {
        // GIVEN
        hookah = null;

        // WHEN
        HookahDTO mappedHookahDTO = hookahMapper.mapHookahToHookahDTO(hookah);

        // THEN
        assertNull(mappedHookahDTO);
    }

    @Test
    public void testMapHookahDTOToHookahShouldMapSuccessful() {
        // GIVEN

        hookahDTO.setName("Name");
        hookahDTO.setWeight(80);
        hookahDTO.setBrand("Brand");
        hookahDTO.setOrigin("Origin");
        hookahDTO.setMaterial("Material");
        hookahDTO.setColor(Color.RED);
        hookahDTO.setPrice(80.00);
        hookahDTO.setHeight(100);
        hookahDTO.setInformation("More Info");

        // WHEN
        Hookah mappedHookah = hookahMapper.mapHookahDTOToHookah(hookahDTO);

        // THEN
        assertEquals(mappedHookah.getName(), hookahDTO.getName());
        assertEquals(mappedHookah.getWeight(), hookahDTO.getWeight());
        assertEquals(mappedHookah.getBrand(), hookahDTO.getBrand());
        assertEquals(mappedHookah.getOrigin(), hookahDTO.getOrigin());
        assertEquals(mappedHookah.getMaterial(), hookahDTO.getMaterial());
        assertEquals(mappedHookah.getColor(), hookahDTO.getColor());
        assertEquals(mappedHookah.getPrice(), hookahDTO.getPrice());
        assertEquals(mappedHookah.getHeight(), hookahDTO.getHeight());
        assertEquals(mappedHookah.getInformation(), hookahDTO.getInformation());
    }

    @Test
    public void testMapHookahDTOToHookahShouldReturnNullPointerIfTheHookahIsNull() {
        // GIVEN
        hookahDTO = null;

        // WHEN
        Hookah mappedHookah = hookahMapper.mapHookahDTOToHookah(hookahDTO);

        // THEN
        assertNull(mappedHookah);
    }
}