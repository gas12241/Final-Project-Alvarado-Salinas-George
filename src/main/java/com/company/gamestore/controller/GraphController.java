package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepo;

    @Autowired
    FeeRepository feeRepo;

//    @Autowired
//    GameRepository gameRepo;

    @Autowired
    InvoiceRepository invoiceRepo;

    @Autowired
    TaxRepository taxRepo;

    @Autowired
    TshirtRepository tshirtRepo;

    @QueryMapping
    public List<Console> consoles() {
        return consoleRepo.findAll();
    }

    @QueryMapping
    public Optional<Console> findConsoleById(@Argument Integer console_id) {
        return consoleRepo.findById(console_id);
    }

    @QueryMapping
    public List<Console> findConsolesByManufacturer(@Argument String manufacturer) {
        // List<Console> consolesByManufacturer = new ArrayList<>();
        return consoleRepo.findByManufacturer(manufacturer);
    }

}
