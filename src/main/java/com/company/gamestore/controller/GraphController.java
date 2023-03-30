package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
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
    GameRepository gameRepo;

    @QueryMapping
    public List<Game> games() {
        return gameRepo.findAll();
    }

    @QueryMapping
    public Optional<Game> findGameById(@Argument Integer game_id) {
        return gameRepo.findById(game_id);
    }

    @QueryMapping
    public List<Game> findGamesByTitle(@Argument String title) {
        return gameRepo.findByTitle(title);
    }

    @QueryMapping
    public List<Game> findGamesByEsrbRating(@Argument String esrb_rating) {
        return gameRepo.findByEsrbRating(esrb_rating);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepo.findByStudio(studio);
    }

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
        return consoleRepo.findByManufacturer(manufacturer);
    }
}
