package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceLayer invoiceServiceLayer;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createInvoice() throws Exception {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(1);
        invoice.setName("John Doe");
        invoice.setStreet("Ok Avenue");
        invoice.setCity("Norfolk");
        invoice.setState("Alabama");
        invoice.setZipcode("12345");
        invoice.setItemType("tshirt");
        invoice.setItemId(1);
        invoice.setQuantity(10);

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoices").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}