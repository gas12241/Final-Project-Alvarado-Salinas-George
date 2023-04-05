package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ConsoleRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "console" table.
 */
@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    /**
     * Method to Get all the Consoles made by the given manufacturer.
     * @param manufacturer String taken in to check for Consoles
     *                     made by them.
     * @return Returns a List of Consoles made by the given manufacturer.
     */
    List<Console> findByManufacturer(String manufacturer);
}
