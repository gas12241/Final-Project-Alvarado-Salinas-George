package com.company.gamestore.service;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.TaxRepository;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceServiceLayerTest {

    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    TshirtRepository tshirtRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setup() {
        invoiceServiceLayer.deleteAll();
        taxRepository.deleteAll();
        feeRepository.deleteAll();
        tshirtRepository.deleteAll();
    }

    public Fee createFee() {
        Fee fee = new Fee();
        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        return fee;
    }

    public Tax createTax() {
        Tax tax = new Tax();
        tax.setState("PA");
        tax.setRate(BigDecimal.valueOf(.07));
        return tax;
    }

    public Tshirt createTShirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        return tshirt;
    }

    public Invoice createInvoice(Tax tax, Tshirt tshirt) {
        Invoice invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("100 Happy Ave");
        invoice.setCity("Zootopea");
        invoice.setState(tax.getState());
        invoice.setZipcode("12345");
        invoice.setItemType("tshirt");
        invoice.setItemId(tshirt.getTshirtId());
        invoice.setQuantity(10);
        return invoice;
    }

    @Test
    public void save() throws Exception {
        Fee fee = createFee();
        feeRepository.save(fee);

        Tax tax = createTax();
        taxRepository.save(tax);

        Tshirt tshirt = createTShirt();
        tshirtRepository.save(tshirt);

        Invoice invoice = createInvoice(tax, tshirt);
        Invoice savedInvoice = invoiceServiceLayer.save(invoice);
        savedInvoice.setTax(savedInvoice.getTax().setScale(2, RoundingMode.HALF_EVEN));


        Optional<Invoice> invoiceRes = invoiceRepository.findById(savedInvoice.getInvoiceId());
        assertEquals(invoiceRes.get(), savedInvoice);
    }


    @Test
    public void findAll() throws Exception {
        Fee fee = createFee();
        feeRepository.save(fee);

        Tax tax = createTax();
        taxRepository.save(tax);

        Tshirt tshirt = createTShirt();
        tshirtRepository.save(tshirt);

        Invoice invoice = createInvoice(tax, tshirt);
        Invoice savedInvoice = invoiceServiceLayer.save(invoice);
        savedInvoice.setTax(savedInvoice.getTax().setScale(2, RoundingMode.HALF_EVEN));

        Optional<Invoice> invoiceRes = invoiceRepository.findById(savedInvoice.getInvoiceId());
        assertEquals(invoiceRes.get(), savedInvoice);
    }
}