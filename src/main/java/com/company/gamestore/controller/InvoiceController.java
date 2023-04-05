package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Invoice Controller that will be used in a Game Store Application.
 * Along with the InvoiceServiceLayer, allows you to do CRUD with
 * the Invoice Database.
 */
@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    /**
     * Method to Get all the Invoices in the Invoice Database.
     * @return Returns a List of all the Invoice Objects in
     *         the Invoice Database.
     */
    @GetMapping(path = "/invoices")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceServiceLayer.findAll();
    }

    /**
     * Method to Get a specific Invoice by its Id.
     * @param invoiceId The Id of the Invoice Object you are trying to get.
     * @return Returns the Invoice Object you are trying to get with their
     *         Id if it is in the Database.
     */
    @GetMapping(path = "/invoices/{invoiceId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int invoiceId) {
        return invoiceServiceLayer.findById(invoiceId);
    }

    /**
     * Method to Get a specific Invoice by its Id.
     * @param name The name attached to the Invoices you are trying to get.
     * @return Returns a List of all the Invoice Objects with the name equal
     *         to the one given.
     */
    @GetMapping(path = "/invoices/name/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByName(@PathVariable String name) {
        return invoiceServiceLayer.findByName(name);
    }


    /**
     * Method to Create a new Invoice.
     * @param invoiceViewModel Uses the invoiceViewModel to get the
     *                         variables for a new Invoice Object.
     * @return Returns the saved invoice.
     */
    @PostMapping(path = "/invoices")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
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


        return saveInvoice;
    }
}
