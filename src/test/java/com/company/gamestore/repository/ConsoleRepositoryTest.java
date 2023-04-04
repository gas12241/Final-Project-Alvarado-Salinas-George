package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {
    // We will test:
    // Create
    // Read
    // Read All
    // Update
    // Delete
    // By Manufacturer

    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void testCreateConsole(){
    Console console = new Console();
    console.setPrice(BigDecimal.valueOf(199.99));
    console.setModel("Gamebox 13");
    console.setManufacturer("Games Live");
    console.setProcessor("MMD 4000");
    console.setQuantity(1);
    console = consoleRepository.save(console);

    List<Console> console1 = consoleRepository.findByManufacturer(console.getManufacturer());
    assertEquals(console1.get(0),console);
    }

    @Test
    public void testGetConsoleById(){
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());
        assertEquals(console1.get(),console);
    }

    @Test
    public void testGetAllConsoles(){
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console = consoleRepository.save(console);

        Console console2 = new Console();
        console2.setPrice(BigDecimal.valueOf(299.99));
        console2.setModel("Gamebox 14");
        console2.setManufacturer("Games Live");
        console2.setProcessor("MMD 4500");
        console2.setQuantity(1);
        console2 = consoleRepository.save(console2);

        List<Console> consoleCheck = consoleRepository.findAll();
        assertEquals(consoleCheck.get(1),console2);
    }

    @Test
    public void testUpdateConsole(){
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console = consoleRepository.save(console);

        console.setModel("Gamebox 14");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4500");
        console = consoleRepository.save(console);

        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());
        assertEquals(console1.get(),console);
    }

    @Test
    public void testDeleteConsole(){
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console = consoleRepository.save(console);

        consoleRepository.deleteById(console.getConsoleId());
        Optional<Console> console1 = consoleRepository.findById(console.getConsoleId());
        assertFalse(console1.isPresent());
    }

    @Test
    public void testGetConsolesByManufacturer(){
        Console console = new Console();
        console.setPrice(BigDecimal.valueOf(199.99));
        console.setModel("Gamebox 13");
        console.setManufacturer("Games Live");
        console.setProcessor("MMD 4000");
        console.setQuantity(1);
        console = consoleRepository.save(console);

        List<Console> console1 = consoleRepository.findByManufacturer(console.getManufacturer());
        assertEquals(console1.get(0),console);
    }
}