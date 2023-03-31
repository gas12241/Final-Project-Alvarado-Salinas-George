package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping(path="/invoices")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path="/invoices/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByName(@PathVariable String name) {
        return invoiceRepository.findInvoiceByName(name);
    }

    @PostMapping(path=("/invoices"))
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createInvoice(@RequestBody InvoiceViewModel invoiceViewModel) throws Exception {
        serviceLayer.createInvoice(invoiceViewModel);
    }

}
