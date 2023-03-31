package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Tax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxRepositoryTest {

    @Autowired
    TaxRepository taxRepo;

    Tax tax1;
    Tax tax2;

    @Before
    public void setUp() throws Exception {
        taxRepo.deleteAll();

        tax1 = new Tax();
        tax1.setState("WA");
        tax1.setRate(BigDecimal.valueOf(.0105));
        taxRepo.save(tax1);

        tax2 = new Tax();
        tax2.setState("CA");
        tax2.setRate(BigDecimal.valueOf(.095));
        taxRepo.save(tax2);
    }

    // Test Post
    @Test
    public void testCreateTax() {
        Tax newTax = new Tax();
        newTax.setState("PA");
        newTax.setRate(BigDecimal.valueOf(.075));
        taxRepo.save(newTax);

        assertEquals(3, taxRepo.findAll().size());
    }

    // Test Update
    @Test
    public void testUpdateTax() {
        tax1.setRate(BigDecimal.valueOf(.055));
        taxRepo.save(tax1);

        Optional<Tax> taxFromRepo = taxRepo.findById(tax1.getState());
        assertEquals(taxFromRepo.get().getRate(), tax1.getRate());
    }

    // Test Get all
    @Test
    public void testGetAllTaxes() {
        assertEquals(2, taxRepo.findAll().size());
    }

    // Test Get by ID
    @Test
    public void testGetTaxById() {
        Optional<Tax> taxFromRepo = taxRepo.findById(tax1.getState());
        assertEquals(taxFromRepo.get(), tax1);
    }

    // Test Delete
    // Test Get by ID
    @Test
    public void testDeleteTax() {
        taxRepo.deleteById(tax1.getState());
        Optional<Tax> taxFromRepo = taxRepo.findById(tax1.getState());
        assertFalse(taxFromRepo.isPresent());
    }
}