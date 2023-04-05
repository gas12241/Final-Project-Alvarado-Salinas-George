package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * GameRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "game" table.
 */
public interface GameRepository extends JpaRepository<Game, Integer> {

    /**
     * Custom Method to find Games by the given studio
     * @param studio used to get all games made by this studio.
     * @return Returns a List of Games with the given studio.
     */
    List<Game> findByStudio(String studio);

    /**
     * Custom Method to find Games by the esrbRating.
     * @param esrbRating used to get all Games with this esrbRating.
     * @return Returns a List of Games with the same esrbRating.
     */
    List<Game> findByEsrbRating(String esrbRating);

    /**
     * Custom Method to find games containing the given title.
     * @param title used to find games with partially
     *              matching title.
     * @return Returns a List of Games with the title
     *         or parts of the title matching the
     *         given title.
     */
    List<Game> findByTitleContaining(String title);
}
