package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/invoices")
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;



    @GetMapping(path = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(@RequestAttribute InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(invoice.getName());
        return serviceLayer.saveInvoice(invoice);
    }
}
