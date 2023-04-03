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

    @Test
    public void saveInvoice() {
        Tax tax = new Tax();
        Fee fee = new Fee();
        Tshirt tshirt = new Tshirt();
        Invoice invoice = new Invoice();


        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));
        feeRepository.save(fee);

        tax.setState("PA");
        tax.setRate(BigDecimal.valueOf(.075));
        taxRepository.save(tax);

        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirtRepository.save(tshirt);


        invoice.setName("John Doe");
        invoice.setStreet("100 Happy Ave");
        invoice.setCity("Zootopea");
        invoice.setState(tax.getState());
        invoice.setZipcode("12345");
        invoice.setItemType("tshirt");
        invoice.setItemId(tshirt.getTshirtId());
        invoice.setQuantity(10);
        Invoice savedInvoice = invoiceServiceLayer.save(invoice);

        Optional<Invoice> invoiceRes = invoiceRepository.findById(savedInvoice.getInvoiceId());
        System.out.println(invoiceRes.get());
        System.out.println(invoiceRes.get());
        assertEquals(invoiceRes.get(), savedInvoice);
    }
}