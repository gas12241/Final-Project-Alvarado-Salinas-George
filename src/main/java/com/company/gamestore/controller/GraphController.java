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

/**
 * GraphController that will Query data from the Database using
 * GraphQL.
 */
@Controller
public class GraphController {

    /**
     * Repos that will be used to get data from.
     */
    @Autowired
    ConsoleRepository consoleRepo;

    @Autowired
    GameRepository gameRepo;

    /**
     * QueryMapping to Get all Games from the Game Database.
     * @return Returns a List of all the Games in the Game Database.
     */
    @QueryMapping
    public List<Game> games() {
        return gameRepo.findAll();
    }

    /**
     * QueryMapping to find a game by its Id.
     * @param game_id game_id of the game you are looking
     *                for in the Database.
     * @return Returns a Game from the Database that matches the
     *         Id given if it exists in the Database.
     */
    @QueryMapping
    public Optional<Game> findGameById(@Argument Integer game_id) {
        return gameRepo.findById(game_id);
    }

    /**
     * QueryMapping to find Games that include a specific title.
     * @param title title being used to check for games that
     *              contain it anywhere in the title.
     * @return Returns a List of Games whose title include the title given.
     */
    @QueryMapping
    public List<Game> findGamesByTitle(@Argument String title) {
        return gameRepo.findByTitleContaining(title);
    }

    /**
     * QueryMapping to find Games by their esrbRating.
     * @param esrb_rating esrb_rating used to only get back
     *                    the games that have that rating.
     * @return Returns a List of Games that have the following esrb_rating
     */
    @QueryMapping
    public List<Game> findGamesByEsrbRating(@Argument String esrb_rating) {
        return gameRepo.findByEsrbRating(esrb_rating);
    }

    /**
     * QueryMapping to find Games by their Studio.
     * @param studio studio used to get only games back
     *               made by the given studio.
     * @return Returns a List of Games made by the studio given.
     */
    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepo.findByStudio(studio);
    }

    /**
     * QueryMapping to get all the Consoles from the Database.
     * @return Returns a List of all the Consoles in the Database.
     */
    @QueryMapping
    public List<Console> consoles() {
        return consoleRepo.findAll();
    }

    /**
     * QueryMapping to find a Console by the given Id.
     * @param console_id console_id being used to find a
     *                   specific Console in the Database.
     * @return Returns a Console with the Given Id attached to it
     *         if it exists in the Database.
     */
    @QueryMapping
    public Optional<Console> findConsoleById(@Argument Integer console_id) {
        return consoleRepo.findById(console_id);
    }

    /**
     * QueryMapping to find Consoles by their Manufacturer.
     * @param manufacturer manufacturer given to find Consoles
     *                     made by them.
     * @return Returns a List of Consoles made by the given Manufacturer.
     */
    @QueryMapping
    public List<Console> findConsolesByManufacturer(@Argument String manufacturer) {
        return consoleRepo.findByManufacturer(manufacturer);
    }
}
