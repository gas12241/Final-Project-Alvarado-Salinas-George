package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
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

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    ;

    public Invoice findById(int invoiceId) {
        Optional<Invoice> res = invoiceRepository.findById(invoiceId);
        return res.isPresent() ? res.get() : null;
    }

    public List<Invoice> findByName(String name) {
        return invoiceRepository.findByName(name);
    }

    ;

    @Transactional
    public Invoice save(Invoice invoice) {
        int itemId = invoice.getItemId();
        String itemType = invoice.getItemType().toLowerCase();
        int productQuantity = 0;
        Boolean isPresent = false;
        BigDecimal productPrice = new BigDecimal(0);

        String consoleKey = "console";
        String gameKey = "game";
        String tshirtKey = "tshirt";

        Console consoleProduct = null;
        Game gameProduct = null;
        Tshirt tshirtProduct = null;


        // Verify if valid product type is provided
        Boolean isValidItemType = itemType.equals(consoleKey) || itemType.equals(gameKey) || itemType.equals(tshirtKey);
        if (!isValidItemType) throw new IllegalArgumentException("Invalid item type provided");

        // Verify if product exists, check if quantity is available, and set the product price
        if (itemType.equals(consoleKey)) {
            Optional<Console> res = consoleRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();
            consoleProduct = res.get();

        } else if (itemType.equals(gameKey)) {
            Optional<Game> res = gameRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();
            gameProduct = res.get();
        } else if (itemType.equals(tshirtKey)) {
            Optional<Tshirt> res = tshirtRepository.findById(itemId);
            isPresent = res.isPresent();
            productPrice = res.get().getPrice();
            productQuantity = res.get().getQuantity();
            tshirtProduct = res.get();
        }

        // Return null if item does not exit
        if (!isPresent) throw new IllegalArgumentException("Item does not exist");
        ;

        // set unit price
        invoice.setUnitPrice(productPrice);


        // Check if product there are enough product  quantity
        if (productQuantity <= 0 || productQuantity < invoice.getQuantity())
            throw new IllegalArgumentException("Order quantity too many");
        ;

        // Set processing fee
        Optional<Fee> resFee = feeRepository.findById(invoice.getItemType());
        BigDecimal invoiceProcessingFee = resFee.get().getFee();
        if (invoice.getQuantity() > 10) invoice.setProcessingFee(invoiceProcessingFee.add(BigDecimal.valueOf(15.49)));
        else invoice.setProcessingFee(invoiceProcessingFee);

        // Set product id
        invoice.setItemId(itemId);

        // Calculate subtotal
        BigDecimal subtotal = productPrice.multiply(new BigDecimal(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        String invoiceState = invoice.getState();

        // Set invoice tax
        Optional<Tax> resStateTax = taxRepository.findById(invoiceState);
        if (!resStateTax.isPresent()) throw new IllegalArgumentException("Invalid state provided");
        ;
        ;
        invoice.setTax(invoice.getSubtotal().multiply(resStateTax.get().getRate()).stripTrailingZeros());


        // Calculate total
        BigDecimal total = subtotal.add(invoice.getProcessingFee()).add(invoice.getTax());
        invoice.setTotal(total.stripTrailingZeros());

        // save invoice and return
        invoiceRepository.save(invoice);

        // Reduce product quantity
        if (itemType.equals(consoleKey)) {
            consoleProduct.setQuantity(consoleProduct.getQuantity() - invoice.getQuantity());
            consoleRepository.save(consoleProduct);
        } else if (itemType.equals(gameKey)) {
            gameProduct.setQuantity(gameProduct.getQuantity() - invoice.getQuantity());
            gameRepository.save(gameProduct);
        } else if (itemType.equals(tshirtKey)) {
            tshirtProduct.setQuantity(tshirtProduct.getQuantity() - invoice.getQuantity());
            tshirtRepository.save(tshirtProduct);
        }

        return invoice;
    }


    public void deleteAll() {
        invoiceRepository.deleteAll();
    }
}