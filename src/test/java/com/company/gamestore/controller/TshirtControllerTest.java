package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TshirtRepository tshirtRepository;
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void getTshirtByColor() throws Exception {

        mockMvc.perform(get("/tshirt/green"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTshirtBySize() throws Exception {
        mockMvc.perform(get("/tshirt/large"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getTshirts() throws Exception {
        mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTshirtById() throws Exception {
        mockMvc.perform(get("/tshirt/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void createTshirt() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);
        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(post("/tshirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteTshirt() throws Exception{
        mockMvc.perform(delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateTshirt() throws Exception{
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);
        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(put("tshirt").contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}