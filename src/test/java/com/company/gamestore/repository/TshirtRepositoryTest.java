package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TshirtRepositoryTest {
    // Create
    // Read
    // Read All
    // Update
    // Delete
    // By Color
    // By Size
    @Autowired
    TshirtRepository tshirtRepository;
    @Before
    public void setup() {
        tshirtRepository.deleteAll();
    }
    @Test
    public void testGetTshirtById() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");
        tshirt.setPrice(BigDecimal.valueOf(12L, 2));
        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt.setTshirtId(1);
        tshirt = tshirtRepository.save(tshirt);
        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertTrue(tshirt1.isPresent());
    }
    @Test
    public void testGetTshirtByColor() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt = tshirtRepository.save(tshirt);


        List<Tshirt> tshirt1 = tshirtRepository.findByColor("green");
        assertEquals(tshirt1.get(0), tshirt);
    }
    @Test
    public void testGetTshirtBySize() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt = tshirtRepository.save(tshirt);

        List<Tshirt> tshirt1 = tshirtRepository.findBySize("large");
        assertEquals(tshirt1.get(0), tshirt);
    }

    @Test
    public void testGetAllTshirts() {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setColor("green");
        tshirt1.setDescription("nike t-shirt for children");

        tshirt1.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt1.setSize("large");
        tshirt1.setQuantity(100);
        tshirt1 = tshirtRepository.save(tshirt1);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("yellow");
        tshirt2.setDescription("adidas t-shirt for adults");
        tshirt2.setPrice(BigDecimal.valueOf(20));
        tshirt2.setSize("medium");
        tshirt2.setQuantity(50);
        tshirt2 = tshirtRepository.save(tshirt2);

        List<Tshirt> tshirts = tshirtRepository.findAll();
        assertEquals(tshirts.size(), 2);
    }
    @Test
    public void testCreateTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertEquals(tshirt1.get(), tshirt);
    }

    @Test
    public void testUpdateTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt = tshirtRepository.save(tshirt);

        tshirt.setSize("small");
        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertEquals(tshirt1.get(), tshirt);
    }

    @Test
    public void testDeleteTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("green");
        tshirt.setDescription("nike t-shirt for children");

        tshirt.setPrice(BigDecimal.valueOf(12L, 2));

        tshirt.setSize("large");
        tshirt.setQuantity(100);
        tshirt = tshirtRepository.save(tshirt);

        tshirtRepository.deleteById(tshirt.getTshirtId());
        Optional<Tshirt> tshirt1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertFalse(tshirt1.isPresent());
    }
}