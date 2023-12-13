package com.cloud.rebellion.demo.service;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.NoSuchFieldTypeException;
import com.cloud.rebellion.demo.exception.bowl.NoSuchBowlException;
import com.cloud.rebellion.demo.exception.hookah.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.BowlMapper;
import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.model.entity.Bowl;
import com.cloud.rebellion.demo.model.entity.Hookah;
import com.cloud.rebellion.demo.repository.BowlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BowlServiceTest {

    @Mock
    private BowlRepository bowlRepository;

    @Mock
    private BowlMapper bowlMapper;

    @InjectMocks
    private BowlService bowlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllBowlsShouldGetAllBowlSuccessful() {
        // Arrange
        List<Bowl> bowlList = Arrays.asList(new Bowl(), new Bowl());
        when(bowlRepository.findAll()).thenReturn(bowlList);
        when(bowlMapper.mapBowlToBowlDTO(any())).thenReturn(new BowlDTO());

        // Act
        List<BowlDTO> allBowls = bowlService.getAllBowls();

        // Assert
        assertNotNull(allBowls);
        assertEquals(2, allBowls.size());

        // Verify
        verify(bowlRepository, times(1)).findAll();
        verify(bowlMapper, times(2)).mapBowlToBowlDTO(any());
    }

    @Test
    public void testGetAllBowlsShouldReturnEmptyListIfThereAreNoBowls() {
        // Arrange
        when(bowlRepository.findAll()).thenReturn(new ArrayList<>());
        when(bowlMapper.mapBowlToBowlDTO(any())).thenReturn(new BowlDTO());

        // Act
        List<BowlDTO> allBowls = bowlService.getAllBowls();

        // Assert
        assertEquals(0, allBowls.size());

        // Verify
        verify(bowlRepository, times(1)).findAll();
        verify(bowlMapper, never()).mapBowlToBowlDTO(any());
    }

    @Test
    public void testGetBowlByIdShouldGetBowlSuccessful() {
        // Arrange
        int bowlIdWeWant = 1;
        when(bowlRepository.findById(bowlIdWeWant)).thenReturn(Optional.of(new Bowl()));
        when(bowlMapper.mapBowlToBowlDTO(any())).thenReturn(new BowlDTO());

        // Act
        BowlDTO bowlById = bowlService.getBowlById(bowlIdWeWant);

        // Assert
        assertNotNull(bowlById);

        // Verify
        verify(bowlRepository, times(1)).findById(bowlIdWeWant);
        verify(bowlMapper, times(1)).mapBowlToBowlDTO(any());
    }

    @Test
    public void testGetBowlByIdShouldThrowIfThereIsNoSuchBowl() {
        // Arrange
        when(bowlRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act and assert
        assertThrows(NoSuchBowlException.class, () ->
                bowlService.getBowlById(anyInt()),
                "There is no such bowl");

        // Verify
        verify(bowlRepository, times(1)).findById(anyInt());
        verify(bowlMapper, never()).mapBowlToBowlDTO(any());
    }

    @Test
    public void testGetBowlByNameShouldGetBowlSuccessful() {
        // Arrange
        String name = "Name";
        Bowl bowl = new Bowl();
        bowl.setName(name);
        BowlDTO bowlDTO = new BowlDTO();
        bowlDTO.setName(name);

        when(bowlRepository.findBowlByName(name)).thenReturn(Optional.of(bowl));
        when(bowlMapper.mapBowlToBowlDTO(bowl)).thenReturn(bowlDTO);

        // Act
        BowlDTO bowlByName = bowlService.getBowlByName(name);

        // Assert
        assertNotNull(bowlByName);
        assertSame(bowlByName, bowlDTO);

        // Verify
        verify(bowlRepository, times(1)).findBowlByName(name);
        verify(bowlMapper, times(1)).mapBowlToBowlDTO(bowl);
    }

    @Test
    public void testGetBowlByNameShouldThrowIfThereIsNoSuchBowl() {
        // Arrange
        when(bowlRepository.findBowlByName(anyString())).thenReturn(Optional.empty());

        // Act and assert
        assertThrows(NoSuchBowlException.class,
                () -> bowlService.getBowlByName(anyString()),
                "There is no such bowl");

        // Verify
        verify(bowlRepository, times(1)).findBowlByName(anyString());
        verify(bowlMapper, never()).mapBowlToBowlDTO(any());
    }

    @Test
    public void testCreateNewBowlShouldCreateNewBowlSuccessful() {
        // Arrange
        BowlDTO providedBowl = new BowlDTO();
        Bowl mappedBowlDTOProvided = new Bowl();

        BowlDTO returnedSavedBowlDTO = new BowlDTO();

        when(bowlRepository.save(mappedBowlDTOProvided)).thenReturn(mappedBowlDTOProvided);
        when(bowlMapper.mapBowlToBowlDTO(mappedBowlDTOProvided)).thenReturn(returnedSavedBowlDTO);
        when(bowlMapper.mapBowlDTOToBowl(providedBowl)).thenReturn(mappedBowlDTOProvided);

        // Act
        BowlDTO newCreatedBowl = bowlService.createNewBowl(providedBowl);

        // Assert
        assertNotNull(newCreatedBowl);

        // Verify
        verify(bowlMapper,times(1)).mapBowlDTOToBowl(providedBowl);
        verify(bowlRepository, times(1)).save(mappedBowlDTOProvided);
        verify(bowlMapper, times(1)).mapBowlToBowlDTO(mappedBowlDTOProvided);
    }

    @Test
    public void testDeleteBowlByIdShouldDeleteBowlSuccessful() {
        // Arrange
        int idToDelete = 1;

        // Act
        bowlService.deleteBowlById(idToDelete);

        // Assert
        // Verify
        verify(bowlRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testDeleteBowlByNameShouldDeleteBowlSuccessful() {
        // Arrange
        String bowlName = "Name";
        Bowl bowlToDelete = new Bowl();

        when(bowlRepository.findBowlByName(bowlName)).thenReturn(Optional.of(bowlToDelete));
        // Act
        bowlService.deleteBowlByName(bowlName);

        // Assert
        // Verify
        verify(bowlRepository, times(1)).findBowlByName(bowlName);
        verify(bowlRepository, times(1)).delete(bowlToDelete);
    }

    @Test
    public void testDeleteBowlByNameShouldThrowIfThereIsNoSuchBowlWithThisName() {
        // Arrange
        String bowlName = "Name";

        when(bowlRepository.findBowlByName(bowlName)).thenReturn(Optional.empty());

        // Act
        assertThrows(NoSuchBowlException.class, () -> bowlService.deleteBowlByName(bowlName),
                "You want to delete non-existing bowl");

        // Assert
        // Verify
        verify(bowlRepository, times(1)).findBowlByName(bowlName);
        verify(bowlRepository,never()).delete(any());
    }

    @Test
    public void testUpdateExistingBowlShouldUpdateSuccessful() {
        // Arrange
        int bowlIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("name", "new Name");
        fieldsWeWantToUpdate.put("price", 88.88);
        fieldsWeWantToUpdate.put("color", "RED");

        Bowl presentBowlWeWntToUpdate = new Bowl();
        presentBowlWeWntToUpdate.setName("old Name");
        presentBowlWeWntToUpdate.setPrice(100.08);
        presentBowlWeWntToUpdate.setColor(Color.BLACK);

        BowlDTO updatedBowlDTO = new BowlDTO();
        updatedBowlDTO.setName("new Name");
        updatedBowlDTO.setPrice(88.88);
        updatedBowlDTO.setColor(Color.RED);

        when(bowlRepository.findById(bowlIdWeWantToUpdate)).thenReturn(Optional.of(presentBowlWeWntToUpdate));
        when(bowlRepository.save(presentBowlWeWntToUpdate)).thenReturn(presentBowlWeWntToUpdate);
        when(bowlMapper.mapBowlToBowlDTO(presentBowlWeWntToUpdate)).thenReturn(updatedBowlDTO);

        // Act
        BowlDTO updatedAndSavedBowl = bowlService.updateExistingBowl(bowlIdWeWantToUpdate, fieldsWeWantToUpdate);

        // Assert
        assertSame(updatedBowlDTO, updatedAndSavedBowl);

        // Verify
        verify(bowlRepository, times(1)).findById(bowlIdWeWantToUpdate);
        verify(bowlRepository, times(1)).save(presentBowlWeWntToUpdate);
        verify(bowlMapper, times(1)).mapBowlToBowlDTO(presentBowlWeWntToUpdate);
    }

    @Test
    public void testUpdateExistingBowlShouldThrowIfThereIsNoSuchBowl() {
        // Arrange
        int bowlIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("name", "new Name");

        when(bowlRepository.findById(bowlIdWeWantToUpdate)).thenReturn(Optional.empty());

        // Act assert
        assertThrows(NoSuchBowlException.class, () ->
                bowlService.updateExistingBowl(bowlIdWeWantToUpdate, fieldsWeWantToUpdate),
                "The provided bowl what you want to modify is not present");

        // Verify
        verify(bowlRepository, times(1)).findById(bowlIdWeWantToUpdate);
        verify(bowlRepository, never()).save(any());
        verify(bowlMapper, never()).mapBowlToBowlDTO(any());
    }

    @Test
    public void testUpdateExistingBowlShouldThrowIfFieldKeyIsEmpty() {
        // Arrange
        int bowlIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("", "new Name");

        Bowl presentBowl = new Bowl();
        presentBowl.setName("Name");

        when(bowlRepository.findById(bowlIdWeWantToUpdate)).thenReturn(Optional.of(presentBowl));

        // Act and Assert
        assertThrows(EmptyPatchMapFieldsException.class, () ->
                bowlService.updateExistingBowl(bowlIdWeWantToUpdate, fieldsWeWantToUpdate),
                "One of the field/fields is empty or with wrong data type");

        // Verify
        verify(bowlRepository, times(1)).findById(bowlIdWeWantToUpdate);
        verify(bowlRepository, never()).save(any());
        verify(bowlMapper, never()).mapBowlToBowlDTO(any());
    }
}