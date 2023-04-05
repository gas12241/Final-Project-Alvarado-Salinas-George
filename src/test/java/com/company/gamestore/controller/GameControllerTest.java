package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.FeeRepository;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private Game game;

    private Game noTitleGame;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setTitle("Resident Evil 4");
        game.setEsrbRating("M");
        game.setPrice(BigDecimal.valueOf(59.99));
        game.setDescription("Resident Evil 4 is a survival horror game " +
                "developed by Capcom Production Studio 4 and published by Capcom " +
                "for the GameCube in 2005.");
        game.setStudio("Capcom");
        game.setQuantity(500);

        noTitleGame = new Game();
        noTitleGame.setEsrbRating("M");
        noTitleGame.setPrice(BigDecimal.valueOf(59.99));
        noTitleGame.setDescription("Resident Evil 4 is a survival horror game " +
                "developed by Capcom Production Studio 4 and published by Capcom " +
                "for the GameCube in 2005.");
        noTitleGame.setStudio("Capcom");
        noTitleGame.setQuantity(500);
    }

    @Test
    void createGame() throws Exception {
        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(post("/game")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateGame() throws Exception {
        game.setStudio("Nintendo");
        String inputJson = mapper.writeValueAsString(game);
        mockMvc.perform(
                        put("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    void getAllGames() throws Exception {

        Game game2 = new Game();
        game2.setTitle("Resident Evil 8");
        game2.setEsrbRating("M");
        game2.setPrice(BigDecimal.valueOf(59.99));
        game2.setDescription("Resident Evil 8 is a survival horror game " +
                "developed by Capcom Production Studio 4 and published by Capcom " +
                "in 2021.");
        game2.setStudio("Capcom");

        gameRepo.save(game2);

        mockMvc.perform(MockMvcRequestBuilders.get("/game"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGameById() throws Exception {
        String inputJson = mapper.writeValueAsString(game);
        mockMvc.perform(MockMvcRequestBuilders.get("/game/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void findGamesByStudio() throws Exception {
        mockMvc.perform(get("/game/studio/capcom"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGamesByEsrbRating() throws Exception {
        mockMvc.perform(get("/game/esrbrating/M"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findGamesByTitle() throws Exception {
        mockMvc.perform(get("/game/title/Resident Evil 8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteGame() throws Exception {
        mockMvc.perform(delete("/game/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void failFindGameById() throws Exception {
        String inputJson = mapper.writeValueAsString(game);
        mockMvc.perform(MockMvcRequestBuilders.get("/game/wrongId")).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void failCreateGame() throws Exception {
        String inputJson = mapper.writeValueAsString(noTitleGame);

        mockMvc.perform(post("/game")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}