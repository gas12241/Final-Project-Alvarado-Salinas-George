package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@RestController
public class GameController {

    @Autowired
    GameRepository gameRepo;

    /**
     *
     * @param game
     * @return
     */
    @PostMapping(path = "/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }


    // Update a Game
    @PutMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public Game updateGame(@RequestBody Game game) {
        return gameRepo.save(game);
    }

    // Get all Games
    @GetMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    // Get a Game by Id
    @GetMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Game> findGameById(@PathVariable Integer gameId) {
        return gameRepo.findById(gameId);
    }

    // Delete a game
    @DeleteMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer gameId) {
        gameRepo.deleteById(gameId);
    }


    // get games by studio
    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByStudio(@PathVariable String studio) {
        return gameRepo.findByStudio(studio);
    }

    // get games by ESRB rating
    @GetMapping("/game/esrbrating/{esrbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByEsrbRating(@PathVariable String esrbRating) {
        return gameRepo.findByEsrbRating(esrbRating);
    }

    // Find game by title
    @GetMapping("/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> findGamesByTitle(@PathVariable String title) {


        return gameRepo.findByTitleContaining(title);
    }
}
