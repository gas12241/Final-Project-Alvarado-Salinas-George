package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void postConsole() throws Exception {
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console.setConsoleId(1);
        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(post("/console").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void putConsole() throws Exception {
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console.setConsoleId(1);
        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(put("/console").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteConsole() throws Exception {
        mockMvc.perform(delete("/console/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllConsoles() throws Exception {
        mockMvc.perform(get("/console"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getConsoleById() throws Exception {
        mockMvc.perform(get("/console/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getConsolesByManufacturer() throws Exception {
        mockMvc.perform(get("/console/manufacturer/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailPostConsole() throws Exception {
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console.setConsoleId(1);
        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(post("/console").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailGetConsoleById() throws Exception {
        mockMvc.perform(get("/console/InvalidId"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}