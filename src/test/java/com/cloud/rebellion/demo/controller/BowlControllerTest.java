package com.cloud.rebellion.demo.controller;

import com.cloud.rebellion.demo.exception.EmptyPatchMapFieldsException;
import com.cloud.rebellion.demo.exception.bowl.NoSuchBowlException;
import com.cloud.rebellion.demo.exception.hookah.NoSuchHookahException;
import com.cloud.rebellion.demo.mapper.BowlMapper;
import com.cloud.rebellion.demo.model.dto.BowlDTO;
import com.cloud.rebellion.demo.model.dto.HookahDTO;
import com.cloud.rebellion.demo.model.entity.Bowl;
import com.cloud.rebellion.demo.service.BowlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BowlController.class)
class BowlControllerTest {

    @MockBean
    private BowlMapper bowlMapper;

    @MockBean
    private BowlService bowlService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllBowlsShouldReturnAllBowlsSuccessful() throws Exception {
        // Arrange
        BowlDTO mockFirstBowl = new BowlDTO();
        BowlDTO mockSecondBowl = new BowlDTO();

        List<BowlDTO> bowlDTOList = List.of(mockFirstBowl, mockSecondBowl);
        when(bowlService.getAllBowls()).thenReturn(bowlDTOList);

        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/bowls"));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bowlDTOList.size()));
    }

    @Test
    public void testGetBowlByIdShouldGetBowlSuccessful() throws Exception {
        // Arrange
        int bowlIdWeWantToGet = 1;
        BowlDTO mockBowl = new BowlDTO();
        mockBowl.setName("Name");
        mockBowl.setPrice(80.88);

        when(bowlService.getBowlById(bowlIdWeWantToGet)).thenReturn(mockBowl);

        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/bowls/find-by-id/{id}", bowlIdWeWantToGet));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(mockBowl.getName()))
                .andExpect(jsonPath("$.price").value(mockBowl.getPrice()));
    }

    @Test
    public void testGetBowlByIdShouldReturnNotFoundIfThereIsNoSuchHookah() throws Exception {
        // Arrange
        int bowlIdWeWantToGet = 1;

        when(bowlService.getBowlById(bowlIdWeWantToGet))
                .thenThrow(new NoSuchBowlException("There is no such bowl"));

        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/bowls/find-by-id/{id}", bowlIdWeWantToGet));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("There is no such bowl"));
    }

    @Test
    public void testGetBowlByNameShouldGetBowlSuccessful() throws Exception {
        // Arrange
        String bowlName = "Name";
        BowlDTO bowlDTO = new BowlDTO();
        bowlDTO.setName(bowlName);

        when(bowlService.getBowlByName(bowlName)).thenReturn(bowlDTO);

        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/bowls//find-by-name/{name}", bowlName));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(bowlDTO.getName()));
    }

    @Test
    public void getBowlByNameShouldReturnNotFoundIfThereIsNoSuchBowlWithThisName() throws Exception {
        // Arrange
        String bowlName = "Name";

        when(bowlService.getBowlByName(bowlName))
                .thenThrow(new NoSuchBowlException("There is no such bowl"));

        // Act
        ResultActions resultActions = mockMvc
                .perform(get("/bowls/find-by-name/{name}",bowlName));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("There is no such bowl"));
    }

    @Test
    public void testCreateNewBowlShouldCreateNewBowlSuccessful() throws Exception {
        // Arrange
        BowlDTO bowlDTO = new BowlDTO();

        when(bowlService.createNewBowl(any(BowlDTO.class))).thenReturn(bowlDTO);

        // Act
        ResultActions resultActions = mockMvc
                .perform(post("/bowls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bowlDTO)));

        // Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testDeleteBowlByIdShouldDeleteBowlSuccessful() throws Exception {
        // Arrange
        int bowlIdWeWantToDelete = 1;

        doNothing().when(bowlService)
                .deleteBowlById(bowlIdWeWantToDelete); // void method

        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/bowls/delete-by-id/{id}",bowlIdWeWantToDelete));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBowlByNameShouldDeleteBowlSuccessful() throws Exception {
        // Arrange
        String bowlNameToDelete = "Name";

        doNothing().when(bowlService)
                .deleteBowlByName(bowlNameToDelete);
        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/bowls/delete-by-name/{name}", bowlNameToDelete));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBowlByNameShouldReturnNotFoundIfThereIsNoSuchBowl() throws Exception {
        // Arrange
        String bowlName = "Name";

        doThrow(new NoSuchBowlException("You want to delete non-existing bowl"))
                .when(bowlService).deleteBowlByName(bowlName);
        // Act
        ResultActions resultActions = mockMvc
                .perform(delete("/bowls/delete-by-name/{name}", bowlName));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("You want to delete non-existing bowl"));
    }

    @Test
    public void testUpdateBowlShouldUpdateBowlSuccessful() throws Exception {
        // Arrange
        int bowlId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "New Name");
        BowlDTO bowlDTOToReturn = new BowlDTO();
        bowlDTOToReturn.setName("New Name");

        when(bowlService.updateExistingBowl(bowlId, fields))
                .thenReturn(bowlDTOToReturn);

        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/bowls/{id}", bowlId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(bowlDTOToReturn.getName()));
    }

    @Test
    public void testUpdateBowlShouldReturnBadRequestIfTheFieldGivenHasEmptyKey() throws Exception {
        // Arrange
        int bowlId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("", "New Name");

        when(bowlService.updateExistingBowl(bowlId, fields))
                .thenThrow(new EmptyPatchMapFieldsException("One of the field/fields is empty or with wrong data type"));
        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/bowls/{id}", bowlId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("One of the field/fields is empty or with wrong data type"));
    }

    @Test
    public void testUpdateBowlShouldReturnNotFoundIfTheWantedBowlIsNotPresent() throws Exception {
        // Arrange
        int bowlId = 1;
        Map<String, Object> fields = new HashMap<>();
        fields.put("name", "New Name");

        when(bowlService.updateExistingBowl(bowlId, fields))
                .thenThrow(new NoSuchHookahException("The provided bowl what you want to modify is not present"));

        // Act
        ResultActions resultActions = mockMvc
                .perform(patch("/bowls/{id}", bowlId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fields)));

        // Assert
        resultActions.andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(jsonPath("$").value("The provided bowl what you want to modify is not present"));
    }
}