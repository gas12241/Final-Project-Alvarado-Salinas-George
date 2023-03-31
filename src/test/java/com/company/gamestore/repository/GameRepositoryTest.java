package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepo;

    Game game1;
    Game game2;
    Game game3;

    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();

//        game1 = new Game();
////        game1.setGameId();
//        game1.setTitle();
//        game1.setEsrbRating();
//        game1.setPrice();
//        game1.setDescription();
    }

    // Test Post

    // Test Put

    // Test get all Games

    // Test get a game by Id

    // Test Delete Game

    // Test get Games by Studio

    // Test get games by ESRB Rating

    // Test find game by title
}