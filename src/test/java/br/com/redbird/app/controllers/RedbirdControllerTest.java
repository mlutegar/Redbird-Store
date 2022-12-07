package br.com.redbird.app.controllers;

import br.com.redbird.domain.model.Roupa;
import br.com.redbird.services.RedbirdServiceTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders .*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers .*;

public class RedbirdControllerTest {

    @ExtendWith(SpringExtension.class)
    @WebMvcTest
    public class RedBirdControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        private RedbirdServiceTest redbirdServiceTest;

        @Test
        void getAllRoupas() throws Exception {
            List<Roupa> roupaList = new ArrayList<>();
            roupaList.add(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, new Date(), new Date()));
            roupaList.add(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, new Date(), new Date()));
            Mockito.when(redbirdServiceTest.findAll()).thenReturn(roupaList);

            mockMvc.perform(get("/roupas").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", Matchers.hasSize(2))).andDo(print());
        }

        @Test
        void getRoupa() throws Exception {
            var roupa = new Roupa(1L, "Vestido", "azul", "marisa", "P", 22.10, 2, new Date(), new Date());
            Mockito.when(redbirdServiceTest.findById(roupa.getProductId())).thenReturn(roupa);

            mockMvc.perform(get("/roupa/" + roupa.getProductId()).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(new ObjectMapper().writeValueAsString(roupa))).andExpect(status().isOk()).andDo(print());
        }

        @Test
        void createRoupa() throws Exception {
            roupaList.add(new Roupa(1L, "Vestido", "azul", "marisa", "P", 22.10, 2, new Date(), new Date()));
            Mockito.when(roupa.save(any(Roupa.class))).thenReturn(roupa);
            var mapper = new ObjectMapper();
            var jsonToDo = mapper.writeValueAsString(roupa);

            var resultActions = mockMvc.perform(post("/roupa").contentType(MediaType.APPLICATION_JSON).content(jsonRoupa));
            resultActions.andExpect(status().isCreated()).andExpect(content().json(jsonRoupa)).andExpect(jsonPath("$.description").value("Entrar em férias"));
        }


        @Test
        void editRoupa() throws Exception {
            var roupa = new Roupa(1L, "Vestido", "azul", "marisa", "P", 22.10, 2, new Date(), new Date());
            Mockito.when(redbirdServiceTest.saveRoupa(any(Roupa.class))).thenReturn(roupa);

            var mapper = new ObjectMapper();
            var jsonToDo = mapper.writeValueAsString(roupa);

            var resultActions = mockMvc.perform(
                    post("/roupa")
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
            var roupaServiceMock = mock(RedbirdServiceTest.class);
            doNothing().when(roupaServiceMock).deleteById(any(UUID.class));

            mockMvc.perform(delete("/roupa/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        }

    }
}
