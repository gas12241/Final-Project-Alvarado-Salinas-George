package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.FeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FeeController.class)
public class FeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeeRepository feeRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Fee fee;
    private Fee noFee;

    @Before
    public void setUp() throws Exception {
        fee = new Fee();
        fee.setFee(BigDecimal.valueOf(12.99));
        fee.setProductType("pizza");

        noFee = new Fee();
        noFee.setProductType("Produce");
    }

    @Test
    public void shouldCreateFee() throws Exception {
        String inputJson = mapper.writeValueAsString(fee);

        mockMvc.perform(post("/fees")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAFeeUsingId() throws Exception {
        String inputJson = mapper.writeValueAsString(fee);

        mockMvc.perform(put("/fees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldGetAFeeById() throws Exception {
        mockMvc.perform(get("/fees/candy"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllFees() throws Exception {
        mockMvc.perform(get("/fees"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteFee() throws Exception {
        mockMvc.perform(delete("/fees/candy"))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailCreateFeeAndThrowUnprocessableEntity() throws Exception {
        String inputJson = mapper.writeValueAsString(noFee);

        mockMvc.perform(post("/fees")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}