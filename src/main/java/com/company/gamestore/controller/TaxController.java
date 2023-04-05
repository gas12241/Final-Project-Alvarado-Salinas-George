package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Tax Controller that will be used in a Game Store Application.
 * Allows you to do CRUD with the Tax Database.
 */
@RestController
public class TaxController {

    @Autowired
    TaxRepository taxRepo;

    /**
     * Method to Create Tax data in the Database.
     * @param tax tax Object that will be added to the Database.
     * @return Returns the Tax Object that you inserted with the
     *         Id, letting you know the Post went through.
     */
    @PostMapping("/taxes")
    @ResponseStatus(HttpStatus.CREATED)
    public Tax createTax(@RequestBody @Valid Tax tax) {
        return taxRepo.save(tax);
    }

    /**
     * Method to Update Tax data in the Database.
     * @param tax tax Object that will be used to Update the database.
     *            Should include the Id of the Tax Object you are trying
     *            to Update.
     * @return Returns the Object that just got updated, letting you know the
     *         information was correct.
     */
    @PutMapping("/taxes")
    @ResponseStatus(HttpStatus.OK)
    public Tax updateTax(@RequestBody @Valid Tax tax) {
        return taxRepo.save(tax);
    }

    /**
     * Method to Get a specific Tax Object from the Database.
     * @param taxId The Id of the Tax Object you are trying
     *              to get from the Database.
     * @return Returns the Object you are trying to get with their Id.
     */
    @GetMapping("/taxes/{taxId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Tax> getTaxById(@PathVariable String taxId) {
        return taxRepo.findById(taxId);
    }

    /**
     * Method to Get all Tax Objects from the Database.
     * @return Returns a List of all the Tax Objects
     *         in the Tax Database.
     */
    @GetMapping("/taxes")
    @ResponseStatus(HttpStatus.OK)
    public List<Tax> getAllTaxRates() {
        return taxRepo.findAll();
    }

    /**
     * Method to Delete a Tax Object from the Database.
     * @param taxId The Id of the Tax Object you are
     *              trying to Delete in the Database.
     */
    @DeleteMapping("/taxes/{taxId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTax(@PathVariable String taxId) {
        taxRepo.deleteById(taxId);
    }
}
