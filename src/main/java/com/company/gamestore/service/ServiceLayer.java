package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private ConsoleRepository consoleRepository;
    private FeeRepository feeRepository;
    private GameRepository gameRepository;
    private TaxRepository taxRepository;
    private TshirtRepository tshirtRepository;
    private InvoiceRepository invoiceRepository;

    enum ItemType {console, tshirt, game};


    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository,
                        FeeRepository feeRepository,
                        GameRepository gameRepository,
                        TaxRepository taxRepository,
                        TshirtRepository tshirtRepository,
                        InvoiceRepository invoiceRepository) {
        this.consoleRepository = consoleRepository;
        this.feeRepository = feeRepository;
        this.gameRepository = gameRepository;
        this.taxRepository = taxRepository;
        this.tshirtRepository = tshirtRepository;
        this.invoiceRepository = invoiceRepository;
    }
    @Transactional
    public Invoice createInvoice(InvoiceViewModel invoiceViewModel) throws Exception {
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setState(invoiceViewModel.getState());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        // Set invoice tax
        Optional<Tax> resStateTax = taxRepository.findById(invoice.getState());
        if (!resStateTax.isPresent()) {
            throw new Exception("Invalid State");
        }
        invoice.setTax(resStateTax.get().getRate());

        // Set processing fee
        Optional<Fee> resFee = feeRepository.findById(invoice.getItemType());
        if (!resFee.isPresent()) {
            throw new Exception("Invalid ItemType");
        }
        invoice.setProcessingFee(resFee.get().getFee());

        // check if quantity is available
        int quantity = 0;
        switch (ItemType.valueOf(invoice.getItemType())) {
            case console:
                Optional<Console> console = consoleRepository.findById(invoiceViewModel.getItemId());
                if (console.isPresent()) {
                    quantity = console.get().getQuantity();
                    invoice.setUnitPrice(console.get().getPrice());
                }
                break;
            case tshirt:
                Optional<Tshirt> tshirt = tshirtRepository.findById(invoiceViewModel.getItemId());
                if (tshirt.isPresent()) {
                    quantity = tshirt.get().getQuantity();
                    invoice.setUnitPrice(tshirt.get().getPrice());
                }
                break;
            case game:
                Optional<Game> game = gameRepository.findById(invoiceViewModel.getItemId());
                if (game.isPresent()) {
                    quantity = game.get().getQuantity();
                    invoice.setUnitPrice(invoice.getUnitPrice());
                }
                break;
            default:
                throw new Exception("Invalid ItemType");
        }

        // Calculate subtotal and total
        if (quantity < invoiceViewModel.getQuantity()) {
            throw new Exception("We don't have so many items!");
        } else {
            BigDecimal subtotal = invoice.getUnitPrice().multiply(BigDecimal.valueOf(invoiceViewModel.getQuantity()));
            BigDecimal total = calculateTotal(subtotal, invoice.getQuantity());
            invoice.setTotal(total);
            invoice.setSubtotal(subtotal);
        }

        // save invoice and return
        invoice = invoiceRepository.save(invoice);
        return invoice;

    }

    private BigDecimal calculateTotal(BigDecimal subtotal, int quantity) {
        // TODO
        return BigDecimal.valueOf(1);
    }

}
