package com.cloud.rebellion.demo.controller;

import com.cloud.rebellion.demo.enums.Color;
import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.InvalidObjectFieldsException;
import com.cloud.rebellion.demo.exception.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.HookahMapper;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.service.HookahService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(HookahController.class)
class HookahControllerTest {

    @MockBean
    private HookahMapper hookahMapper;

    @MockBean
    HookahService hookahService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllHookahsShouldReturnAllHookahsSuccessful() throws Exception {
        // Arrange
        HookahDTO mockFirstHookah = new HookahDTO();
        HookahDTO mockSecondHookah = new HookahDTO();

        List<HookahDTO> hookahDTOList = List.of(mockFirstHookah, mockSecondHookah);
        when(hookahService.getAllHookahs()).thenReturn(hookahDTOList);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/hookahs"));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(hookahDTOList.size()));
    }

    @Test
    public void testGetHookahByIdShouldGetHookahSuccessful() throws Exception {
        // Arrange
        int hookahIdWeWantToGet = 1;
        HookahDTO mockFirstHookah = new HookahDTO();
        mockFirstHookah.setName("Name");
        mockFirstHookah.setWeight(80);
        mockFirstHookah.setBrand("Makloud");

        when(hookahService.getHookahById(hookahIdWeWantToGet)).thenReturn(mockFirstHookah);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/hookahs/find-by-id/{id}", hookahIdWeWantToGet));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(mockFirstHookah.getName()))
                .andExpect(jsonPath("$.weight").value(mockFirstHookah.getWeight()))
                .andExpect(jsonPath("$.brand").value(mockFirstHookah.getBrand()));
    }

    @Test
    public void testGetHookahByIdShouldReturnNotFoundIfThereIsNoSuchHookah() throws Exception {
        // Arrange
        int hookahIdWeWantToGet = 1;

        when(hookahService.getHookahById(hookahIdWeWantToGet))
                .thenThrow(new NoSuchHookahException("There is no such hookah"));

        // Act
        ResultActions resultActions = mockMvc.perform(get("/hookahs/find-by-id/{id}", hookahIdWeWantToGet));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("There is no such hookah"));
    }

    @Test
    public void testGetHookahByNameShouldGetHookahSuccessful() throws Exception {
        // Arrange
        String hookahName = "Needed Name";
        HookahDTO foundHookah = new HookahDTO();
        foundHookah.setName(hookahName);

        when(hookahService.getHookahByName(hookahName)).thenReturn(foundHookah);
        // Act
        ResultActions resultActions = mockMvc.perform(get("/hookahs/find-by-name/{name}", hookahName));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(foundHookah.getName()));
    }

    @Test
    public void testGetHookahByNameShouldReturnNotFoundIfThereIsNoSuchHookah() throws Exception {
        // Arrange
        String invalidHookahName = "Needed Name";

        when(hookahService.getHookahByName(invalidHookahName))
                .thenThrow(new NoSuchHookahException("The hookah that you want to get is not present"));

        // Act
        ResultActions resultActions = mockMvc.perform(get("/hookahs/find-by-name/{name}", invalidHookahName));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("The hookah that you want to get is not present"));
    }

    @Test
    public void testCreateNewHookahShouldCreateNewHookahSuccessful() throws Exception {
        // Arrange
        HookahDTO mockedHookahForRequest = new HookahDTO();
        HookahDTO mockedHookahForResponse = new HookahDTO();

        when(hookahService.createNewHookah(any(HookahDTO.class))).thenReturn(mockedHookahForResponse);
        // Act
        ResultActions resultActions = mockMvc.perform(post("/hookahs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockedHookahForResponse)));

        // Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testDeleteHookahByIdShouldDeleteHookahSuccessful() throws Exception {
        // Arrange
        int hookahIdWeWantToDelete = 1;

        doNothing().when(hookahService).deleteById(hookahIdWeWantToDelete);
        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/hookahs//delete-by-id/{id}", hookahIdWeWantToDelete));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteHookahByNameShouldDeleteHookahSuccessful() throws Exception {
        // Arrange
        String hookahName = "Hookah";

        doNothing().when(hookahService).deleteByName(hookahName);
        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/hookahs/delete-by-name/{name}", hookahName));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteHookahByNameShouldReturnNotFoundWhenThereIsNoSuchHookah() throws Exception {
        // Arrange
        String hookahName = "Hookah";

        doThrow(new NoSuchHookahException("You want to delete non-existing hookah"))
                .when(hookahService).deleteByName(hookahName);

        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/hookahs//delete-by-name/{name}", hookahName));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("You want to delete non-existing hookah"));
    }

    @Test
    public void testUpdateHookahShouldUpdateHookahSuccessful() throws Exception {
        // Arrange
        int hookahId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "New Name");
        HookahDTO hookahDTOToReturn = new HookahDTO();
        hookahDTOToReturn.setName("New Name");

        when(hookahService.updateExistingHookah(hookahId, fields))
                .thenReturn(hookahDTOToReturn);

        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/hookahs//{id}", hookahId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hookahDTOToReturn.getName()));
    }

    @Test
    public void testUpdateHookahShouldReturnNotFoundIfTheWantedHookahHasEmptyKeyField() throws Exception {
        // Arrange
        int hookahId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "New Name");

        when(hookahService.updateExistingHookah(hookahId, fields))
                .thenThrow(new EmptyPatchMapFieldsException("One of the field/fields is empty or with wrong data type"));
        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/hookahs//{id}", hookahId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("One of the field/fields is empty or with wrong data type"));
    }

    @Test
    public void testUpdateHookahShouldReturnNotFoundIfTheWantedHookahIsNotPresent() throws Exception {
        // Arrange
        int hookahId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "New Name");

        when(hookahService.updateExistingHookah(hookahId, fields))
                .thenThrow(new NoSuchHookahException("The provided hookah what you want to modify is not present"));

        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/hookahs//{id}", hookahId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("The provided hookah what you want to modify is not present"));
    }
}