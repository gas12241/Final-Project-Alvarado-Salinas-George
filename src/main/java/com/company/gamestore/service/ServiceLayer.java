package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ServiceLayer {

    private ConsoleRepository consoleRepository;
    private FeeRepository feeRepository;
    private GameRepository gameRepository;
    private TaxRepository taxRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;


    @Autowired
    public ServiceLayer(
            ConsoleRepository consoleRepository,
            FeeRepository feeRepository,
            GameRepository gameRepository,
            TaxRepository taxRepository,
            TshirtRepository tshirtRepository,
            InvoiceRepository invoiceRepository
    ) {
        this.consoleRepository = consoleRepository;
        this.feeRepository = feeRepository;
        this.gameRepository = gameRepository;
        this.taxRepository = taxRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        int itemId = invoice.getItemId();
        String itemType = invoice.getItemType();
        Boolean isPresent = false;
        BigDecimal productPrice = new BigDecimal(0);

        String consoleKey = "console";
        String gameKey = "game";
        String tshirtKey = "tshirt";


        String invoiceState = invoice.getState();

        // Set invoice tax
        Optional<Tax> resStateTax = taxRepository.findById(invoiceState);
        if (resStateTax.isPresent()) return null;
        invoice.setTax(resStateTax.get().getRate());

        // Verify if valid product type is provided
        Boolean isValidItemType = itemType.equals(consoleKey) || itemType.equals(gameKey) || itemType.equals(tshirtKey);
        if (!isValidItemType) return null;

        // Verify if product exists, check if quantity is available, and set the product price
        if (itemType.equals(consoleKey)) {
            Optional<Console> res = consoleRepository.findById(itemId);
            isPresent = res.isPresent();
            if (isPresent) productPrice = res.get().getPrice();

        } else if (itemType.equals(gameKey)) {
            Optional<Game> res = gameRepository.findById(itemId);
            isPresent = res.isPresent();
            if (isPresent) productPrice = res.get().getPrice();
        } else if (itemType.equals(tshirtKey)) {
            Optional<Tshirt> res = tshirtRepository.findById(itemId);
            isPresent = res.isPresent();
            if (isPresent) productPrice = res.get().getPrice();
        }

        if (!isPresent) return null;

        // Set processing fee
        Optional<Fee> resFee = feeRepository.findById(invoice.getItemType());
        if (!resFee.isPresent()) return null;
        invoice.setProcessingFee(resFee.get().getFee());

        // Set product id
        invoice.setItemId(itemId);

        // Calculate subtotal
        BigDecimal subtotal = productPrice.multiply(new BigDecimal(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        // Calculate total
        BigDecimal total = subtotal.subtract(invoice.getProcessingFee()).subtract(invoice.getTax());
        invoice.setTotal(total);

        // save invoice and return
        invoiceRepository.save(invoice);

        return invoice;
    }
}
