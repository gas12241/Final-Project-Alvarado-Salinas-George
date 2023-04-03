package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.TaxRepository;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
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
    private InvoiceServiceLayer invoiceServiceLayer;

    @MockBean
    private TaxRepository taxRepository;

    @MockBean
    private FeeRepository feeRepository;

    @MockBean
    private TshirtRepository tshirtRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createInvoice() throws Exception {


        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();


        invoiceViewModel.setName("John Doe");
        invoiceViewModel.setStreet("1000 Happy Ave");
        invoiceViewModel.setCity("Norfolk");
        invoiceViewModel.setState("NY");
        invoiceViewModel.setZipcode("12345");
        invoiceViewModel.setItemType("tshirt");
        invoiceViewModel.setItemId(3);
        invoiceViewModel.setQuantity(10);

        String inputJson = mapper.writeValueAsString(invoiceViewModel);

        mockMvc.perform(post("/invoices").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}