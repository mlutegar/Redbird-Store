package br.com.redbird.app.controllers;

import br.com.redbird.domain.model.Roupa;
import br.com.redbird.services.RoupaService;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RedBirdControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RoupaService roupaService;

    @Test
    void getAllRoupas() throws Exception {
        List<Roupa> roupaList = new ArrayList<>();
        roupaList.add(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())));
        roupaList.add(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())));
        Mockito.when(roupaService.findAll()).thenReturn(roupaList);

        mockMvc.perform(get("/redbird/roupas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2))).andDo(print());
    }

    @Test
    void getRoupa() throws Exception {
        var roupa = new Roupa(UUID.fromString("0bc5e75e-1098-4634-a935-a9a2e7e8aacf"), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        Mockito.when(roupaService.findById(roupa.getProductId())).thenReturn(roupa);

        mockMvc.perform(get("/redbird/roupa/id/" + roupa.getProductId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(roupa))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void createRoupa() throws Exception {
        var roupa = new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        Mockito.when(roupaService.saveRoupa(any(Roupa.class))).thenReturn(roupa);

        var mapper = new ObjectMapper();
        var jsonToDo = mapper.writeValueAsString(roupa);

        var resultActions = mockMvc.perform(
                post("/redbird/roupa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToDo)
        );

        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonToDo))
                .andExpect(jsonPath("$.nome").value("Vestido"));
    }

    @Test
    void editRoupa() throws Exception {
        var roupa = new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        Mockito.when(roupaService.saveRoupa(any(Roupa.class))).thenReturn(roupa);

        var mapper = new ObjectMapper();
        var jsonToDo = mapper.writeValueAsString(roupa);

        var resultActions = mockMvc.perform(
                post("/redbird/roupa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToDo)
        );

        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonToDo))
                .andExpect(jsonPath("$.nome").value("Vestido"));
    }

    @Test
    void deleteToDo() throws Exception {
        var roupaServiceMock = mock(RoupaService.class);
        doNothing().when(roupaServiceMock).deleteById(any(UUID.class));

        mockMvc.perform(delete("/redbird/roupa/id/?id=0bc5e75e-1098-4634-a935-a9a2e7e8aacf").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}
