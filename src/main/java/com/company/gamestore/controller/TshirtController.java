package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TshirtController {
    @Autowired
    TshirtRepository tshirtRepository;
    @GetMapping(path = "/tshirt/{color}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirtByColor(@PathVariable String color) {
        return tshirtRepository.findByColor();
    }
    @GetMapping(path = "/tshirt/{size}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirtBySize(@PathVariable String size) {
        return tshirtRepository.findBySize();
    }
    @GetMapping(path = "/tshirt")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getTshirts() {
        List<Tshirt> tshirts =  tshirtRepository.findAll();
        return tshirts;
    }

    @PostMapping(path = "/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt createTshirt(@RequestBody @Valid Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    @DeleteMapping(path = "/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable Integer id) {
        tshirtRepository.deleteById(id);
    }
    @PutMapping(path = "/tshirt")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void updateTshirt(@RequestBody Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }
}
