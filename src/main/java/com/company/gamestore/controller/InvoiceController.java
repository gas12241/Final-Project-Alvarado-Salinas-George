package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController

public class InvoiceController {
    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;


    @PostMapping(path = "/invoices")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(@RequestAttribute InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        Invoice saveInvoice = invoiceServiceLayer.save(invoice);

        if (saveInvoice == null) {
            throw new IllegalArgumentException("Invalid input Provided");
        }

        return saveInvoice;
    }
}
