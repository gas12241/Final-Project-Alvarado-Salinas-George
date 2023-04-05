package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Game Controller class that will be used in a Game Store Application.
 * Will be used to do CRUD with the Game Database.
 */
@RestController
public class GameController {

    @Autowired
    GameRepository gameRepo;

    /**
     * Method to Create Game data in the Database.
     * @param game game Object that will be added to the Database.
     * @return Returns the Game Object that you inserted with a
     *         generated Id, letting you know the Post went through.
     */
    @PostMapping(path = "/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody @Valid Game game) {
        return gameRepo.save(game);
    }


    /**
     * Method to Update Game Data in the Database.
     * @param game game Object that will be used to Update
     *             the Database. Should include the Id of
     *             the Game Object you are trying to update.
     * @return Returns the Game Object that just got Updated
     *         letting you know the information was correct.
     */
    @PutMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public Game updateGame(@RequestBody @Valid Game game) {
        return gameRepo.save(game);
    }

    /**
     * Method to Get all the Games from the Database.
     * @return Returns a List Games currently in the Database.
     */
    @GetMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    /**
     * Method to Get a Game by its Id from the Database.
     * @param gameId gameId used to get a specific
     *               Game from the Database.
     * @return Returns the Game from the Database if it exists.
     */
    @GetMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Game> findGameById(@PathVariable Integer gameId) {
        return gameRepo.findById(gameId);
    }

    /**
     * Method to Delete a Game by its Id from the Database.
     * @param gameId gameId used to Delete a specific
     *               Game from the Database.
     */
    @DeleteMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer gameId) {
        gameRepo.deleteById(gameId);
    }


    /**
     * Method to Get Games by Studio from the Database.
     * @param studio studio used to Get a List of Games
     *               from that specific studio.
     * @return Returns a List of Games made by a specific studio.
     */
    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByStudio(@PathVariable String studio) {
        return gameRepo.findByStudio(studio);
    }

    /**
     * Method to Get Games by esrbRating from the Database.
     * @param esrbRating esrbRating used to Get a List of
     *                   Games from the Database with
     *                   that specific rating.
     * @return Returns a List of Games with the given esrbRating.
     */
    @GetMapping("/game/esrbrating/{esrbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByEsrbRating(@PathVariable String esrbRating) {
        return gameRepo.findByEsrbRating(esrbRating);
    }

    /**
     * Method to Get Games that contain the title given.
     * @param title title used to check games in the Database.
     * @return Returns a List of Games with partial or complete
     * matches of the given title.
     */
    @GetMapping("/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByTitle(@PathVariable String title) {


        return gameRepo.findByTitleContaining(title);
    }
}
