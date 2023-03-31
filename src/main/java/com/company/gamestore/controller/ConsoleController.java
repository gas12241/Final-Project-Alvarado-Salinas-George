package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository consoleRepo;

    //CREATE - Post a console to DB
    @PostMapping(path = "/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console console) {
        return consoleRepo.save(console);
    }

    //UPDATE - Put a console in DB
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public Console updateConsole(@RequestBody Console console) {
        return consoleRepo.save(console);
    }

    //DELETE - Delete a console
    @DeleteMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer consoleId) {
        consoleRepo.deleteById(consoleId);
    }

    //READ - Get all consoles
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleRepo.findAll();
    }

    //READ - Get a console by ID
    @GetMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> findConsoleById(@PathVariable Integer consoleId) {
        return consoleRepo.findById(consoleId);
    }

    //READ - Get consoles by Manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> findConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleRepo.findByManufacturer(manufacturer);
    }
}
