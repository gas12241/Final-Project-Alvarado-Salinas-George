package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
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


import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Tax tax = new Tax();
        Fee fee = new Fee();
        Tshirt tshirt = new Tshirt();


        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        when(feeRepository.findById(fee.getProductType())).thenReturn(Optional.of(fee));

        tax.setState("PA");
        tax.setRate(BigDecimal.valueOf(.075));
        when(taxRepository.findById(tax.getState())).thenReturn(Optional.of(tax));

        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        when(tshirtRepository.findById(tshirt.getTshirtId())).thenReturn(Optional.of(tshirt));

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();


        invoiceViewModel.setName("John Doe");
        invoiceViewModel.setStreet("1000 Happy Ave");
        invoiceViewModel.setCity("Norfolk");
        invoiceViewModel.setState(tax.getState());
        invoiceViewModel.setZipcode("12345");
        invoiceViewModel.setItemType("tshirt");
        invoiceViewModel.setItemId(tshirt.getTshirtId());
        invoiceViewModel.setQuantity(10);

        String inputJson = mapper.writeValueAsString(invoiceViewModel);

        mockMvc.perform(post("/invoices").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    // "/invoices"
    @Test
    public void shouldGetAllInvoicesAndExpectOk() throws Exception {
        mockMvc.perform(get("/invoices"))
                .andDo(print()).andExpect(status().isOk());
    }

    // "/invoices/{invoiceId}"
    @Test
    public void shouldGetAnInvoiceByIdAndExpectOk() throws Exception {
        mockMvc.perform(get("/invoices/12"))
                .andDo(print()).andExpect(status().isOk());
    }

    // "/invoices/name/{name}"
    @Test
    public void shouldGetInvoicesByCustomerNameAndExpectOk() throws Exception {
        mockMvc.perform(get("/invoices/name/George"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFailCreateInvoice() throws Exception {
        Tax tax = new Tax();
        Fee fee = new Fee();
        Tshirt tshirt = new Tshirt();


        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        when(feeRepository.findById(fee.getProductType())).thenReturn(Optional.of(fee));

        tax.setState("PA");
        tax.setRate(BigDecimal.valueOf(.075));
        when(taxRepository.findById(tax.getState())).thenReturn(Optional.of(tax));

        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        when(tshirtRepository.findById(tshirt.getTshirtId())).thenReturn(Optional.of(tshirt));

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        invoiceViewModel.setStreet("1000 Happy Ave");
        invoiceViewModel.setCity("Norfolk");
        invoiceViewModel.setState(tax.getState());
        invoiceViewModel.setZipcode("12345");
        invoiceViewModel.setItemType("tshirt");
        invoiceViewModel.setItemId(tshirt.getTshirtId());
        invoiceViewModel.setQuantity(10);

        String inputJson = mapper.writeValueAsString(invoiceViewModel);

        mockMvc.perform(post("/invoices").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailGetAnInvoiceByIdAndThrowUnprocessableEntity() throws Exception {
        mockMvc.perform(get("/invoices/notARealId"))
                .andDo(print()).andExpect(status().isUnprocessableEntity());
    }
}