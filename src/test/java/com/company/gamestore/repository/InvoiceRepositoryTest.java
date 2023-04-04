package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.InvoiceServiceLayer;
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
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepo;

    @Autowired
    TaxRepository taxRepo;

    @Autowired
    FeeRepository feeRepo;

    @Autowired
    TshirtRepository tshirtRepo;

    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    private Fee fee;

    private Tax tax;

    private Tshirt tshirt;

    private Invoice invoice;

    private Invoice invoice2;

    private Invoice invoice3;

    @Before
    public void setUp() throws Exception {
        invoiceRepo.deleteAll();
        taxRepo.deleteAll();
        feeRepo.deleteAll();
        tshirtRepo.deleteAll();
        invoiceServiceLayer.deleteAll();

        fee = new Fee();
        fee.setProductType("tshirt");
        fee.setFee(BigDecimal.valueOf(.99));

        feeRepo.save(fee);

        tax = new Tax();
        tax.setState("PA");
        tax.setRate(BigDecimal.valueOf(.07));

        taxRepo.save(tax);

        tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12L, 2));
        tshirt.setSize("large");
        tshirt.setQuantity(100);

        tshirtRepo.save(tshirt);

        invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("100 Happy Ave");
        invoice.setCity("Zootopea");
        invoice.setState(tax.getState());
        invoice.setZipcode("12345");
        invoice.setItemType("tshirt");
        invoice.setItemId(tshirt.getTshirtId());
        invoice.setQuantity(10);

        invoice2 = new Invoice();
        invoice2.setName("John Doe");
        invoice2.setStreet("100 Happy Ave");
        invoice2.setCity("Zootopea");
        invoice2.setState(tax.getState());
        invoice2.setZipcode("12345");
        invoice2.setItemType("tshirt");
        invoice2.setItemId(tshirt.getTshirtId());
        invoice2.setQuantity(2);

        invoice3 = new Invoice();
        invoice3.setName("Not John Doe");
        invoice3.setStreet("0 Unhappy Ave");
        invoice3.setCity("Aquarius City");
        invoice3.setState(tax.getState());
        invoice3.setZipcode("54321");
        invoice3.setItemType("tshirt");
        invoice3.setItemId(tshirt.getTshirtId());
        invoice3.setQuantity(20);

        invoice = invoiceServiceLayer.save(invoice);
        invoice.setTax(invoice.getTax().setScale(2, RoundingMode.HALF_EVEN));
        invoice2 = invoiceServiceLayer.save(invoice2);
        invoice2.setTax(invoice2.getTax().setScale(2, RoundingMode.HALF_EVEN));
        invoice3 = invoiceServiceLayer.save(invoice3);
        invoice3.setTax(invoice3.getTax().setScale(2, RoundingMode.HALF_EVEN));
    }


    // Test GetAllInvoices
    @Test
    public void testGetAllInvoices() {
        assertEquals(3, invoiceRepo.findAll().size());
    }

    //Test GetInvoiceById
    @Test
    public void testGetInvoiceById() {
        Optional<Invoice> invoice1FromRepo = invoiceRepo.findById(invoice.getInvoiceId());
        Optional<Invoice> invoice2FromRepo = invoiceRepo.findById(invoice2.getInvoiceId());
        Optional<Invoice> invoice3FromRepo = invoiceRepo.findById(invoice3.getInvoiceId());

        assertEquals(invoice1FromRepo.get(), invoice);
        assertEquals(invoice2FromRepo.get(), invoice2);
        assertEquals(invoice3FromRepo.get(), invoice3);
    }

    //Test Get Invoices by Name
    @Test
    public void testGetInvoiceByName() {
        assertEquals(3, invoiceRepo.findAll().size());
        assertEquals(2, invoiceRepo.findByName("John Doe").size());
    }

    //Test Create Invoice
    @Test
    public void testCreateInvoice() {
        Fee newFee = new Fee();
        newFee.setProductType("tshirt");
        newFee.setFee(BigDecimal.valueOf(.99));

        feeRepo.save(newFee);

        Tax newTax = new Tax();
        newTax.setState("WA");
        newTax.setRate(BigDecimal.valueOf(.10));

        taxRepo.save(newTax);

        Tshirt newTshirt = new Tshirt();
        newTshirt.setColor("red");
        newTshirt.setDescription("Champion t-shirt for children");
        newTshirt.setPrice(BigDecimal.valueOf(12L, 2));
        newTshirt.setSize("large");
        newTshirt.setQuantity(100);

        tshirtRepo.save(newTshirt);

        Invoice newInvoice = new Invoice();
        newInvoice.setName("Shaquille O'neal");
        newInvoice.setStreet("1122 12th drive");
        newInvoice.setCity("Beast City");
        newInvoice.setState(tax.getState());
        newInvoice.setZipcode("11313");
        newInvoice.setItemType("tshirt");
        newInvoice.setItemId(tshirt.getTshirtId());
        newInvoice.setQuantity(10);

        newInvoice = invoiceServiceLayer.save(newInvoice);
        newInvoice.setTax(newInvoice.getTax().setScale(2, RoundingMode.HALF_EVEN));
        Optional<Invoice> invoiceFromRepo = invoiceRepo.findById(newInvoice.getInvoiceId());
        assertEquals(invoiceFromRepo.get(), newInvoice);
        assertEquals(4, invoiceRepo.findAll().size());
    }
}