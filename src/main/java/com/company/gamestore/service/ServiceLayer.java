package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    enum ItemType {Console, Tshirt, Game}

    ;
    public static final BigDecimal additionalProcessingFee = BigDecimal.valueOf(15.49);
    public static final int QUANTITY = 10;

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
    public void createInvoice(InvoiceViewModel invoiceViewModel) throws Exception {
        Invoice invoice = setInvoiceByInvoiceViewModel(invoiceViewModel);

        // Set invoice tax
        Optional<Tax> resStateTax = taxRepository.findById(invoice.getState());
        if (!resStateTax.isPresent()) {
            throw new IllegalArgumentException("Invalid State");
        }
        invoice.setTax(resStateTax.get().getRate());

        // Set processing fee
        Optional<Fee> resFee = feeRepository.findById(invoice.getItemType());
        if (!resFee.isPresent()) {
            throw new IllegalArgumentException("Invalid ItemType");
        }
        invoice.setProcessingFee(resFee.get().getFee());

        // check if itemID exists
        int quantity = 0;
        switch (ItemType.valueOf(invoice.getItemType())) {
            case Console:
                Optional<Console> console = consoleRepository.findById(invoiceViewModel.getItemId());
                if (console.isPresent()) {
                    quantity = console.get().getQuantity();
                    invoice.setItemId(console.get().getConsole_id());
                    invoice.setUnitPrice(console.get().getPrice());
                } else {
                    throw new IllegalArgumentException("Invalid ItemId");
                }
                break;
            case Tshirt:
                Optional<Tshirt> tshirt = tshirtRepository.findById(invoiceViewModel.getItemId());
                if (tshirt.isPresent()) {
                    quantity = tshirt.get().getQuantity();
                    invoice.setItemId(tshirt.get().getTshirtId());
                    invoice.setUnitPrice(tshirt.get().getPrice());
                } else {
                    throw new IllegalArgumentException("Invalid ItemId");
                }
                break;
            case Game:
                Optional<Game> game = gameRepository.findById(invoiceViewModel.getItemId());
                if (game.isPresent()) {
                    quantity = game.get().getQuantity();
                    invoice.setItemId(game.get().getGameId());
                    invoice.setUnitPrice(invoice.getUnitPrice());
                } else {
                    throw new IllegalArgumentException("Invalid ItemId");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid ItemType");
        }

        // check if purchase quantity is valid
        if (quantity < invoiceViewModel.getQuantity()) {
            throw new IllegalArgumentException("We don't have so many items!");
        }

        // set subtotal
        BigDecimal subtotal = invoice.getUnitPrice().multiply(BigDecimal.valueOf(invoiceViewModel.getQuantity()));
        invoice.setSubtotal(subtotal);

        // set processingPrice
        BigDecimal processingPrice = feeRepository.findFeeByProductType(invoice.getItemType()).get().getFee();
        if (invoice.getQuantity() > QUANTITY) {
            processingPrice = processingPrice.add(additionalProcessingFee);
        }
        invoice.setProcessingFee(processingPrice);

        // set tax
        BigDecimal taxRate = taxRepository.findTaxByState(invoice.getState()).get().getRate();
        BigDecimal tax = subtotal.multiply(taxRate);
        invoice.setTax(tax);

        // set total
        BigDecimal total = subtotal.add(tax).add(processingPrice);
        invoice.setTotal(total);

        // save invoice and return
        invoiceRepository.save(invoice);
    }

    private Invoice setInvoiceByInvoiceViewModel(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setState(invoiceViewModel.getState());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());
        return invoice;
    }


}
