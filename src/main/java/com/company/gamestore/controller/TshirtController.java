package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * TaxController that will be used in a Game Store Application.
 * Allows you to do CRUD with the Tshirt Database.
 */
@RestController
public class TshirtController {
    @Autowired
    TshirtRepository tshirtRepository;

    /**
     * Method to Get Tshirts by a specific color.
     * @param color color used to get a List of Tshirts back.
     * @return Returns a List of Tshirts with the given color.
     */
    @GetMapping(path = "/tshirt/color/{color}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirtByColor(@PathVariable String color) {
        return tshirtRepository.findByColor(color);
    }

    /**
     * Method to get Tshirts by their size.
     * @param size size used to get the viable Tshirts back.
     * @return Returns a List of Tshirts with the given size.
     */
    @GetMapping(path = "/tshirt/size/{size}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirtBySize(@PathVariable String size) {
        return tshirtRepository.findBySize(size);
    }

    /**
     * Method to get all the Tshirts in the Database.
     * @return Returns a List of all the Tshirts in the Database.
     */
    @GetMapping(path = "/tshirt")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirts() {
        List<Tshirt> tshirts =  tshirtRepository.findAll();
        return tshirts;
    }

    /**
     * Method to get a Tshirt by its Id.
     * @param id id given to get a specific Tshirt from the Database.
     * @return Returns a specific Tshirt from the Databse with the
     *         given Id if it exists in the Database.
     */
    @GetMapping(path = "/tshirt/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int id) {
       Optional<Tshirt> tshirt = tshirtRepository.findById(id);
       if (tshirt.isPresent()) return tshirt.get();
       return null;
    }

    /**
     * Method to Create a Tshirt, Posting it to the Database.
     * @param tshirt tshirt Object given to Post to the Database.
     * @return Returns the Tshirt you inputted with an auto-generated
     *         Id, letting you know the Post went through.
     */
    @PostMapping(path = "/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt createTshirt(@RequestBody @Valid Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    /**
     * Method to Delete a Tshirt from the Database with the given Id.
     * @param id id used to Delete a specific Tshirt from the Database.
     */
    @DeleteMapping(path = "/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable Integer id) {
        tshirtRepository.deleteById(id);
    }

    /**
     * Method to Update a Tshirt from the Database.
     * @param tshirt tshirt Object will be used to Update an already
     *               existing Tshirt in the Database. Should have
     *               the Id of the Tshirt as well.
     */
    @PutMapping(path = "/tshirt")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }
}
