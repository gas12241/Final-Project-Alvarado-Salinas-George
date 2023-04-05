package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(0);

        List<Tshirt> list = new ArrayList<>();
        list.add(tshirt);

        when(tshirtRepository.findByColor(tshirt.getColor())).thenReturn(list);


        mockMvc.perform(get("/tshirt/color/green"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTshirtBySize() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(0);

        List<Tshirt> list = new ArrayList<>();
        list.add(tshirt);

        when(tshirtRepository.findBySize(tshirt.getSize())).thenReturn(list);

        mockMvc.perform(get("/tshirt/size/large"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(tshirtRepository).findBySize(tshirt.getSize());
    }
    @Test
    public void getTshirts() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(0);

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("yellow");
        tshirt1.setDescription("lululemon t-shirt for adults");
        tshirt1.setPrice(BigDecimal.valueOf(12.00));
        tshirt1.setSize("large");
        tshirt1.setQuantity(50);
        tshirt1.setTshirtId(1);

        List<Tshirt> list = new ArrayList<>();
        list.add(tshirt1);
        list.add(tshirt);

        when(tshirtRepository.findAll()).thenReturn(list);

        mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTshirtById() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(2);
        when(tshirtRepository.save(tshirt)).thenReturn(tshirt);
        mockMvc.perform(get("/tshirt/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void createTshirt() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);
        String inputJson = mapper.writeValueAsString(tshirt);
        when(tshirtRepository.save(tshirt)).thenReturn(tshirt);
        mockMvc.perform(post("/tshirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteTshirt() throws Exception{
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);

        mockMvc.perform(delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(tshirtRepository).deleteById(1);
    }

    @Test
    public void updateTshirt() throws Exception{
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(0);
        when(tshirtRepository.save(tshirt)).thenReturn(tshirt);

        String inputJson = mapper.writeValueAsString(tshirt);
        mockMvc.perform(put("/tshirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailCreateTshirt() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        // tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);
        String inputJson = mapper.writeValueAsString(tshirt);
        when(tshirtRepository.save(tshirt)).thenReturn(tshirt);
        mockMvc.perform(post("/tshirt").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailGetTshirtById() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12.00));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(2);
        when(tshirtRepository.save(tshirt)).thenReturn(tshirt);
        mockMvc.perform(get("/tshirt/notValidId"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}