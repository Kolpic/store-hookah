package com.cloud.rebellion.demo.mapper;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.enums.Finish;
import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.model.entity.Bowl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class BowlMapperTest {

    private BowlMapper bowlMapper = Mappers.getMapper(BowlMapper.class);
    private Bowl bowl = new Bowl();
    private BowlDTO bowlDTO = new BowlDTO();

    @Test
    public void testMapBowlToBowlDTOWithThreeFields() {
        // GIVEN
        Bowl testBowl = new Bowl();
        testBowl.setName("new Name");
        testBowl.setPrice(88.88);
        testBowl.setColor(Color.RED);

        // WHEN
        BowlDTO bowlDTOReturned = bowlMapper.mapBowlToBowlDTO(testBowl);

        // THEN
        assertEquals(testBowl.getName(), bowlDTOReturned.getName());
        assertEquals(testBowl.getPrice(), bowlDTOReturned.getPrice());
        assertEquals(testBowl.getColor(), bowlDTOReturned.getColor());
    }

    @Test
    public void testMapBowlToBowlDTOShouldMapSuccessful() {
        // GIVEN
        bowl.setName("Name");
        bowl.setWeight(80);
        bowl.setBrand("Brand");
        bowl.setMaterial("Material");
        bowl.setOrigin("Origin");
        bowl.setColor(Color.RED);
        bowl.setPrice(80.09);
        bowl.setCapacity(20);
        bowl.setFinish(Finish.GLAZED);
        bowl.setForType("Yellow tobacco");

        // WHEN
        BowlDTO mappedBowlToBowlDTO = bowlMapper.mapBowlToBowlDTO(bowl);

        // THEN
        assertEquals(mappedBowlToBowlDTO.getName(), bowl.getName());
        assertEquals(mappedBowlToBowlDTO.getWeight(), bowl.getWeight());
        assertEquals(mappedBowlToBowlDTO.getBrand(), bowl.getBrand());
        assertEquals(mappedBowlToBowlDTO.getOrigin(), bowl.getOrigin());
        assertEquals(mappedBowlToBowlDTO.getMaterial(), bowl.getMaterial());
        assertEquals(mappedBowlToBowlDTO.getColor(), bowl.getColor());
        assertEquals(mappedBowlToBowlDTO.getPrice(), bowl.getPrice());
        assertEquals(mappedBowlToBowlDTO.getFinish(), bowl.getFinish());
        assertEquals(mappedBowlToBowlDTO.getCapacity(), bowl.getCapacity());
        assertEquals(mappedBowlToBowlDTO.getForType(), bowl.getForType());
    }

    @Test
    public void testMapBowlToBowlDTOShouldReturnNullPointerIfTheBowlIsNull() {
        // GIVEN
        bowl = null;

        // WHEN
        BowlDTO mappedBowlToBowlDTO = bowlMapper.mapBowlToBowlDTO(bowl);

        // THEN
        assertNull(mappedBowlToBowlDTO);
    }

    @Test
    public void testMapBowlDTOtoBowlShouldMapSuccessful() {
        // GIVEN
        bowlDTO.setName("Name");
        bowlDTO.setWeight(80);
        bowlDTO.setBrand("Brand");
        bowlDTO.setMaterial("Material");
        bowlDTO.setOrigin("Origin");
        bowlDTO.setColor(Color.RED);
        bowlDTO.setPrice(80.09);
        bowlDTO.setCapacity(20);
        bowlDTO.setFinish(Finish.GLAZED);
        bowlDTO.setForType("Yellow tobacco");

        // WHEN
        Bowl mappedBowl = bowlMapper.mapBowlDTOToBowl(bowlDTO);

        // THEN
        assertEquals(mappedBowl.getName(), bowlDTO.getName());
        assertEquals(mappedBowl.getWeight(), bowlDTO.getWeight());
        assertEquals(mappedBowl.getBrand(), bowlDTO.getBrand());
        assertEquals(mappedBowl.getOrigin(), bowlDTO.getOrigin());
        assertEquals(mappedBowl.getMaterial(), bowlDTO.getMaterial());
        assertEquals(mappedBowl.getColor(), bowlDTO.getColor());
        assertEquals(mappedBowl.getPrice(), bowlDTO.getPrice());
        assertEquals(mappedBowl.getFinish(), bowlDTO.getFinish());
        assertEquals(mappedBowl.getCapacity(), bowlDTO.getCapacity());
        assertEquals(mappedBowl.getForType(), bowlDTO.getForType());
    }

    @Test
    public void testMapBowlDTOToBowlShouldReturnNullPointerIfTheBowlDTOIsNull() {
        // GIVEN
        bowlDTO = null;

        // WHEN
        Bowl mappedBowlDTOToBowl = bowlMapper.mapBowlDTOToBowl(bowlDTO);

        // THEN
        assertNull(mappedBowlDTOToBowl);
    }
}

