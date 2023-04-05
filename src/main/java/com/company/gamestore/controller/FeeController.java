package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Fee Controller that will be used in a Game Store Application.
 * Allows you to do CRUD with the Fee Database.
 */
@RestController
public class FeeController {

    @Autowired
    FeeRepository feeRepo;


    /**
     * Method to Create Fee data in the Database.
     * @param fee fee Object that will be added to the Database.
     * @return Returns the Fee Object that you inserted with the
     *         Id, letting you know the Post went through.
     */
    @PostMapping("/fees")
    @ResponseStatus(HttpStatus.CREATED)
    public Fee createFee(@RequestBody @Valid Fee fee) {
        return feeRepo.save(fee);
    }

    /**
     * Method to Update Fee data in the Database.
     * @param fee fee Object that will be used to Update the Database.
     *            Should include the Id of the Fee object you
     *            are trying to Update.
     * @return Returns the Fee Object that just got Updated
     *         letting you know the information was correct.
     */
    @PutMapping("/fees")
    @ResponseStatus(HttpStatus.OK)
    public Fee updateFee(@RequestBody @Valid Fee fee) {
        return feeRepo.save(fee);
    }

    /**
     * Method to get a Specific Fee Object from the Database.
     * @param feeId The Id of the Fee Object you are trying to get
     * @return Returns the Fee Object you are trying to get
     *         with their Id if it is in the Database.
     */
    @GetMapping("/fees/{feeId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Fee> getFeeById(@PathVariable String feeId) {
        return feeRepo.findById(feeId);
    }

    /**
     * Method to Get all Fee Objects from the Database.
     * @return Returns a List of all the Fee Objects
     *         in the Fee Database.
     */
    @GetMapping("/fees")
    @ResponseStatus(HttpStatus.OK)
    public List<Fee> getAllFees() {
        return feeRepo.findAll();
    }

    /**
     * Method to Delete a Fee Object from the Database.
     * @param feeId The Id of the Fee Object you are trying
     *              to Delete in the Database.
     */
    @DeleteMapping("/fees/{feeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFee(@PathVariable String feeId) {
        feeRepo.deleteById(feeId);
    }
}
