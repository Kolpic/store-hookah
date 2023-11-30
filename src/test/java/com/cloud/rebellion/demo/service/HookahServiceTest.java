package com.cloud.rebellion.demo.service;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.NoSuchFieldTypeException;
import com.cloud.rebellion.demo.exception.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.HookahMapper;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Hookah;
import com.cloud.rebellion.demo.repository.HookahRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//ExtendWith(MockitoExtension.class)
class HookahServiceTest {

    @Mock
    private HookahRepository hookahRepository;

    @Mock
    private HookahMapper hookahMapper;

    @InjectMocks
    private HookahService hookahService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHookahsShouldGetAllAllHookahsSuccessful() {
        // Arrange
        List<Hookah> mockHookahList = Arrays.asList(new Hookah(), new Hookah());
        when(hookahRepository.findAll()).thenReturn(mockHookahList);
        when(hookahMapper.mapHookahToHookahDTO(any())).thenReturn(new HookahDTO());

        // Act
        List<HookahDTO> result = hookahService.getAllHookahs();

        // Assert
        assertNotNull(result);
        assertEquals(mockHookahList.size(), result.size());

        // Verify
        verify(hookahRepository, times(1)).findAll();
        verify(hookahMapper, times(mockHookahList.size())).mapHookahToHookahDTO(any());
    }

    @Test
    public void testGetAllHookahsShouldGetNullIfThereAreNoHookahs() {
        // Arrange
        List<Hookah> mockHookahList = new ArrayList<>();
        when(hookahRepository.findAll()).thenReturn(mockHookahList);
        when(hookahMapper.mapHookahToHookahDTO(any())).thenReturn(new HookahDTO());

        // Act
        List<HookahDTO> result = hookahService.getAllHookahs();

        // Assert
        assertEquals(0, result.size());

        // Verify
        verify(hookahRepository, times(1)).findAll();
        verify(hookahMapper, times(mockHookahList.size())).mapHookahToHookahDTO(any());
    }

    @Test
    public void testGetHookahByIdShouldGetHookahSuccessful() {
        // Arrange
        Hookah mockHookah = new Hookah();
        when(hookahRepository.findById(anyInt())).thenReturn(Optional.of(mockHookah));
        when(hookahMapper.mapHookahToHookahDTO(any())).thenReturn(new HookahDTO());

        // Act
        HookahDTO result = hookahService.getHookahById(1);

        // Assert
        assertNotNull(result);

        // Verify
        verify(hookahRepository, times(1)).findById(anyInt());
        verify(hookahMapper, times(1)).mapHookahToHookahDTO(any());
    }

    @Test
    public void testGetHookahByIdShouldThrowIfThereIsNoSuchHookah() {
        // Arrange
        when(hookahRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchHookahException.class, () -> hookahService.getHookahById(1),
                "There is no such hookah");

        // Verify
        verify(hookahRepository, times(1)).findById(1);
        verify(hookahMapper, never()).mapHookahToHookahDTO(any());
    }

    @Test
    public void testGetHookahByNameShouldGetTheHookahSuccessful() {
        // Arrange
        String hookahName = "Hookah name";
        Hookah mockHookah = new Hookah();
        HookahDTO mockHookahDTO = new HookahDTO();

        when(hookahRepository.findHookahByName(hookahName)).thenReturn(Optional.of(mockHookah));
        when(hookahMapper.mapHookahToHookahDTO(mockHookah)).thenReturn(mockHookahDTO);

        // Act
        HookahDTO result = hookahService.getHookahByName(hookahName);

        // Assert
        assertNotNull(result);
        assertSame(mockHookahDTO, result);

        // Verify
        verify(hookahRepository, times(1)).findHookahByName(hookahName);
        verify(hookahMapper, times(1)).mapHookahToHookahDTO(mockHookah);
    }

    @Test
    public void testGetHookahByNameShouldThrowIfThereIsNoSuchHookah() {
        // Arrange
        String hookahName = "Non Existing Hookah";
        when(hookahRepository.findHookahByName(hookahName)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchHookahException.class, () -> hookahService.getHookahByName(hookahName),
                "The hookah that you want to get is not present");

        // verify
        verify(hookahRepository, times(1)).findHookahByName(hookahName);
        verifyNoInteractions(hookahMapper);
    }

    @Test
    public void testCreateNewHookahShouldCreateNewHookahSuccessful() {
        // Arrange
        HookahDTO providedHookah = new HookahDTO();
        Hookah hookahToSave = new Hookah();
        HookahDTO savedHookahDTO = new HookahDTO();

        when(hookahRepository.save(hookahToSave)).thenReturn(hookahToSave);
        when(hookahMapper.mapHookahToHookahDTO(hookahToSave)).thenReturn(savedHookahDTO);
        when(hookahMapper.mapHookahDTOToHookah(providedHookah)).thenReturn(hookahToSave);

        // Act
        HookahDTO newHookah = hookahService.createNewHookah(providedHookah);

        // Assert
        assertNotNull(newHookah);

        // Verify
        verify(hookahRepository, times(1)).save(hookahToSave);
        verify(hookahMapper, times(1)).mapHookahToHookahDTO(hookahToSave);
        verify(hookahMapper, times(1)).mapHookahDTOToHookah(providedHookah);
    }

    @Test
    public void testDeleteByIdShouldDeleteHookahSuccessful() {
        // Arrange
        int idToDelete = 1;

        // Act
        hookahService.deleteById(idToDelete);

        // Assert
        // Verify
        verify(hookahRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testDeleteByNameShouldDeleteHookahSuccessful() {
        // Arrange
        String hookahName = "Name";
        Hookah hookahToDelete = new Hookah();

        when(hookahRepository.findHookahByName(hookahName)).thenReturn(Optional.of(hookahToDelete));
        // Act
        hookahService.deleteByName(hookahName);

        // Assert
        // Verify
        verify(hookahRepository, times(1)).findHookahByName(hookahName);
        verify(hookahRepository, times(1)).delete(hookahToDelete);
    }

    @Test
    public void testDeleteByNameShouldThrowIfThereIsNoSuchHookahWithThisName() {
        // Arrange
        String hookahName = "Name";

        when(hookahRepository.findHookahByName(hookahName)).thenReturn(Optional.empty());

        // Act
        assertThrows(NoSuchHookahException.class, () -> hookahService.deleteByName(hookahName),
                "You want to delete non-existing hookah");

        // Assert
        // Verify
        verify(hookahRepository, times(1)).findHookahByName(hookahName);
        verify(hookahRepository,never()).delete(any());
    }

    @Test
    public void testUpdateExistingHookahShouldUpdateSuccessful() {
        // Arrange
        int hookahIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("name", "new Name");
        fieldsWeWantToUpdate.put("price", 88.88);
        fieldsWeWantToUpdate.put("height", 100);
        fieldsWeWantToUpdate.put("color", "RED");

        Hookah hookahWeWantToUpdate = new Hookah();
        hookahWeWantToUpdate.setId(1);
        hookahWeWantToUpdate.setName("new Name");
        hookahWeWantToUpdate.setPrice(88.00);
        hookahWeWantToUpdate.setWeight(100);
        hookahWeWantToUpdate.setColor(Color.RED);

        HookahDTO updatedHookahDTO = new HookahDTO();
        updatedHookahDTO.setName("new Name");
        updatedHookahDTO.setPrice(88.00);
        updatedHookahDTO.setWeight(100);
        updatedHookahDTO.setColor(Color.RED);

        when(hookahRepository.findById(hookahIdWeWantToUpdate)).thenReturn(Optional.of(hookahWeWantToUpdate));
        when(hookahRepository.save(hookahWeWantToUpdate)).thenReturn(hookahWeWantToUpdate);
        when(hookahMapper.mapHookahToHookahDTO(hookahWeWantToUpdate)).thenReturn(updatedHookahDTO);

        // Act
        HookahDTO updatedAndSavedHookah = hookahService.updateExistingHookah(hookahIdWeWantToUpdate, fieldsWeWantToUpdate);

        // Assert
        assertSame(updatedHookahDTO, updatedAndSavedHookah);

        // Verify
        verify(hookahRepository, times(1)).findById(hookahIdWeWantToUpdate);
        verify(hookahRepository, times(1)).save(hookahWeWantToUpdate);
        verify(hookahMapper, times(1)).mapHookahToHookahDTO(hookahWeWantToUpdate);
    }

    @Test
    public void testUpdateExistingHookahShouldThrowIfThereIsNoSuchHookah() {
        // Arrange
        int hookahIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("name", "new Name");

        when(hookahRepository.findById(hookahIdWeWantToUpdate)).thenReturn(Optional.empty());

        // Act
        assertThrows(NoSuchHookahException.class, () ->
                hookahService.updateExistingHookah(hookahIdWeWantToUpdate, fieldsWeWantToUpdate),
                "The provided hookah what you want to modify is not present");

        // Assert
        // Verify
        verify(hookahRepository, times(1)).findById(hookahIdWeWantToUpdate);
        verify(hookahRepository, never()).save(any());
        verify(hookahMapper, never()).mapHookahToHookahDTO(any());
    }

    @Test
    public void testUpdateExistingHookahShouldThrowIfFieldKeyIsNull() {
        // Arrange
        int hookahIdWeWantToUpdate = 1;
        Map<String, Object> fieldsWeWantToUpdate = new HashMap<>();
        fieldsWeWantToUpdate.put("", "new Name");
        fieldsWeWantToUpdate.put("price", "88.88");
        Hookah hookahWeWantToUpdate = new Hookah();

        when(hookahRepository.findById(hookahIdWeWantToUpdate)).thenReturn(Optional.of(hookahWeWantToUpdate));

        // Act
        assertThrows(EmptyPatchMapFieldsException.class, () ->
                hookahService.updateExistingHookah(hookahIdWeWantToUpdate, fieldsWeWantToUpdate),
                "One of the field/fields is empty or with wrong data type");

        // Assert
        // Verify
        verify(hookahRepository, times(1)).findById(hookahIdWeWantToUpdate);
        verify(hookahRepository, never()).save(any());
        verify(hookahMapper, never()).mapHookahToHookahDTO(any());
    }

}