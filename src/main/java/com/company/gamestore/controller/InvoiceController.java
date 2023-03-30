package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/invoices")
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;


    @GetMapping(path = "")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Invoice> createInvoice() {
        return invoiceRepository.findAll();
    }
}
