package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class InvoiceServiceLayer {

    private ConsoleRepository consoleRepository;
    private FeeRepository feeRepository;
    private GameRepository gameRepository;
    private TaxRepository taxRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;


    @Autowired
    public InvoiceServiceLayer(
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
    public Invoice save(Invoice invoice) {
        int itemId = invoice.getItemId();
        String itemType = invoice.getItemType();
        int productQuantity = 0;
        Boolean isPresent = false;
        BigDecimal productPrice = new BigDecimal(0);

        String consoleKey = "console";
        String gameKey = "game";
        String tshirtKey = "tshirt";


        String invoiceState = invoice.getState();

        // Set invoice tax
        Optional<Tax> resStateTax = taxRepository.findById(invoiceState);
        if (!resStateTax.isPresent()) return null;
        invoice.setTax(resStateTax.get().getRate());

        // Verify if valid product type is provided
        Boolean isValidItemType = itemType.equals(consoleKey) || itemType.equals(gameKey) || itemType.equals(tshirtKey);
        if (!isValidItemType) return null;

        // Verify if product exists, check if quantity is available, and set the product price
        if (itemType.equals(consoleKey)) {
            Optional<Console> res = consoleRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();

        } else if (itemType.equals(gameKey)) {
            Optional<Game> res = gameRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();
        } else if (itemType.equals(tshirtKey)) {
            Optional<Tshirt> res = tshirtRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();
        }

        // Return null if item does not exit
        if (!isPresent) return null;

        // Check if product there are enough product  quantity
        if(productQuantity <= 0 || productQuantity < invoice.getQuantity()) return  null;

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
        BigDecimal deductible = invoice.getProcessingFee().add(invoice.getTax());
        if (invoice.getQuantity() > 15.49) deductible.add(new BigDecimal(15.49));
        BigDecimal total = subtotal.subtract(deductible);

        invoice.setTotal(total);

        // save invoice and return
        invoiceRepository.save(invoice);

        return invoice;
    }


    public void deleteAll() {
        invoiceRepository.deleteAll();
    }
}
