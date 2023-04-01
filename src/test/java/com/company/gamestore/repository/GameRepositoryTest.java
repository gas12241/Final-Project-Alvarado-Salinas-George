package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.assertj.core.api.OptionalLongAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepo;

    Game game1;
    Game game2;
    Game game3;
    Game game4;

    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();

        game1 = new Game();
        //game1.setGameId();
        game1.setTitle("Apex Legends");
        game1.setEsrbRating("T");
        game1.setPrice(BigDecimal.valueOf(0.00));
        game1.setDescription("FPS Battle Royal with advanced movement" +
                " and amazing gunplay!");
        game1.setStudio("Raven Software");
        game1.setQuantity(1000);


        game2 = new Game();
        //game2.setGameId();
        game2.setTitle("League of Legends");
        game2.setEsrbRating("T");
        game2.setPrice(BigDecimal.valueOf(0.00));
        game2.setDescription("MOBA where only the best will be left" +
                " standing! Are you in?");
        game2.setStudio("Riot Games");
        game2.setQuantity(1000);


        game3 = new Game();
        // game3.setGameId(2);
        game3.setTitle("Call of Duty: Modern Warfare 2");
        game3.setEsrbRating("M");
        game3.setPrice(BigDecimal.valueOf(69.99));
        game3.setDescription("When they attack, someone will need" +
                " to step up. Will that be you?");
        game3.setStudio("Infinity Ward");
        game3.setQuantity(500);


        game4 = new Game();
        // game4.setGameId(25);
        game4.setTitle("Valorant");
        game4.setEsrbRating("T");
        game4.setPrice(BigDecimal.valueOf(0.00));
        game4.setDescription("5v5 Tac-FPS where you play as unique" +
                " agents each with their own abilities. Who said" +
                " Tac shooters had to be boring?");
        game4.setStudio("Riot Games");
        game4.setQuantity(1000);

        game1 = gameRepo.save(game1);
        game2 = gameRepo.save(game2);
        game3 = gameRepo.save(game3);
        game4 = gameRepo.save(game4);
    }

    // Test Post
    @Test
    public void testCreateGame() {
        Game newGame = new Game();
        //newGame.setGameId();
        newGame.setTitle("Guitar Hero");
        newGame.setEsrbRating("E");
        newGame.setPrice(BigDecimal.valueOf(29.99));
        newGame.setDescription("Guitar game for the musically gifted");
        newGame.setStudio("Activision");
        newGame.setQuantity(500);

        newGame = gameRepo.save(newGame);
        Optional<Game> game1 = gameRepo.findById(newGame.getGameId());
        assertEquals(game1.get(), newGame);
        assertEquals(5, gameRepo.findAll().size());
    }

    // Test Put
    @Test
    public void testUpdateGame() {
        assertEquals(gameRepo.findById(game4.getGameId()).get().getTitle(), "Valorant");
        game4.setTitle("Updated Title");
        gameRepo.save(game4);
        assertEquals(gameRepo.findById(game4.getGameId()).get().getTitle(), "Updated Title");
    }

    //Test Get All Games
    @Test
    public void testGetAllGames() {
        assertEquals(gameRepo.findAll().size(), 4);
    }

    // Test Get Game by Id
    @Test
    public void testFindGameById() {
        Optional<Game> gameFromRepo = gameRepo.findById(game3.getGameId());
        assertEquals(gameFromRepo.get(), game3);
    }

    // Test Delete Game
    @Test
    public void deleteGame() {
        Optional<Game> gameFromRepo = gameRepo.findById(game1.getGameId());
        System.out.println(gameFromRepo);
        assertTrue(gameFromRepo.isPresent());

        gameRepo.deleteById(game1.getGameId());
        Optional<Game> secondGameFromRepo = gameRepo.findById(game1.getGameId());
        assertFalse(secondGameFromRepo.isPresent());
    }

    // Test Get Games by Studio
    @Test
    public void testGetAllGamesByStudio() {
        assertEquals(gameRepo.findByStudio("Riot Games").size(), 2);
    }

    // Test Get Games by Rating
    @Test
    public void testGetAllGamesByRating() {
        // I made games Apex Legends, League of Legends and Valorant above,
        // they all have rating "T" so this should be 3
        assertEquals(gameRepo.findByEsrbRating("T").size(), 3);
    }

    @Test
    public void testGetAllGamesByTitle() {
        // I made games Apex Legends and League of Legends above,
        // so this should be 2
        assertEquals(gameRepo.findByTitleContaining("legends").size(), 2);
    }
}