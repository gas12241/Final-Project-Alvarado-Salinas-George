package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
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
public class FeeRepositoryTest {

    @Autowired
    FeeRepository feeRepo;

    Fee fee1;
    Fee fee2;

    @Before
    public void setUp() throws Exception {
        feeRepo.deleteAll();

        fee1 = new Fee();
        fee1.setProductType("Candy");
        fee1.setFee(BigDecimal.valueOf(.99));
        feeRepo.save(fee1);

        fee2 = new Fee();
        fee2.setProductType("Laptop");
        fee2.setFee(BigDecimal.valueOf(5.00));
        feeRepo.save(fee2);
    }

    // Test Create
    @Test
    public void testCreateFee() {
        Fee newFee = new Fee();
        newFee.setProductType("Tv");
        newFee.setFee(BigDecimal.valueOf(15.00));
        feeRepo.save(newFee);

        assertEquals(3, feeRepo.findAll().size());
    }

    // Test Update
    @Test
    public void testUpdateFee() {
        fee1.setFee(BigDecimal.valueOf(2500.62));
        feeRepo.save(fee1);

        Optional<Fee> feeFromRepo = feeRepo.findById(fee1.getProductType());
        assertEquals(feeFromRepo.get().getFee(), fee1.getFee());
    }

    // Test Get by Id
    @Test
    public void testGetFeeById() {
        Optional<Fee> feeFromRepo = feeRepo.findById("Candy");
        assertEquals(feeFromRepo.get(), fee1);
    }

    // Test Get all
    @Test
    public void testGetAllFees() {
        assertEquals(2, feeRepo.findAll().size());
    }

    // Test Delete
    @Test
    public void testDeleteFee() {
        feeRepo.deleteById(fee1.getProductType());
        Optional<Fee> feeFromRepo = feeRepo.findById(fee1.getProductType());
        assertFalse(feeFromRepo.isPresent());
    }
}