package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Console Controller that will be used in a Game Store Application.
 */
@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository consoleRepo;

    /**
     * Post Method used to Create a Console in the Console Database.
     * @param console console being saved in the Console Database.
     * @return Returns the Console saved in the Database, letting
     *         you know the save went through.
     */
    @PostMapping(path = "/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console console) {
        return consoleRepo.save(console);
    }

    /**
     * Put Method used to Update a Console in the Console Database.
     * @param console console being used to Update an existing
     *                Console in the Database. Should have ConsoleId
     *                attached to update.
     * @return Returns the Console being updated in the Console Database,
     *         allowing you to see the changes go through.
     */
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console updateConsole(@RequestBody @Valid Console console) {
        return consoleRepo.save(console);
    }

    /**
     * Delete Method used to Delete a Console in the Console Database.
     * @param consoleId consoleId being used to find a Console in the
     *                  Database to then Delete it.
     */
    @DeleteMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer consoleId) {
        consoleRepo.deleteById(consoleId);
    }

    /**
     * Get Method to Get all of the Consoles in the Console Database.
     * @return Returns a List of all the Consoles in the
     *         Console Database.
     */
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleRepo.findAll();
    }

    /**
     * Get Method to Get a Console using the ConsoleId
     * @param consoleId consoleId used to find a specific
     *                  Console in the Console Database.
     * @return Returns a Console if the given consoleId
     *         exists in the Database.
     */
    @GetMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> findConsoleById(@PathVariable Integer consoleId) {
        return consoleRepo.findById(consoleId);
    }

    /**
     * Get Method to get Consoles by Manufacturer
     * from the Console Database.
     * @param manufacturer manufacturer used to find Consoles
     *                     in the Console Database.
     * @return Returns a List of all the Consoles with a
     *         specific manufacturer.
     */
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> findConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleRepo.findByManufacturer(manufacturer);
    }
}
