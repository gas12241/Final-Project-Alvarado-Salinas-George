package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.TaxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxController.class)
public class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxRepository taxRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Tax tax;

    private Tax noRate;

    @Before
    public void setUp() throws Exception {
        tax = new Tax();
        tax.setState("NY");
        tax.setRate(BigDecimal.valueOf(.099));

        noRate = new Tax();
        noRate.setState("NY");
    }

    // Post "/taxes" CREATED
    @Test
    public void shouldCreateTax() throws Exception {
        String inputJson = mapper.writeValueAsString(tax);
        mockMvc.perform(post("/taxes")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    // Put "/taxes" OK
    @Test
    public void shouldUpdateATaxUsingId() throws Exception {
        String inputJson = mapper.writeValueAsString(tax);

        mockMvc.perform(put("/taxes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)).andDo(print())
                .andExpect(status().isOk());
    }

    // Get "/taxes/taxId" OK
    @Test
    public void shouldGetATaxById() throws Exception {
        mockMvc.perform(get("/taxes/WA"))
                .andDo(print()).andExpect(status().isOk());
    }

    // Get "/taxes" OK
    @Test
    public void shouldGetAllTaxes() throws Exception {
        mockMvc.perform(get("/taxes"))
                .andDo(print()).andExpect(status().isOk());
    }

    // Delete "/taxes/taxId" NO_CONTENT
    @Test
    public void shouldDeleteTax() throws Exception {
        mockMvc.perform(delete("/taxes/WA"))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailCreateTax() throws Exception {
        String inputJson = mapper.writeValueAsString(noRate);
        mockMvc.perform(post("/taxes")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isUnprocessableEntity());
    }
}