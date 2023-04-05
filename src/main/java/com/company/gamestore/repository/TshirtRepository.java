package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

/**
 * TshirtRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "tshirt" table.
 */
@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {

    /**
     * Method to find Tshirts by color.
     * @param color used to get all the Tshirts of the same color.
     * @return Returns a List of Tshirts matching the given color.
     */
    List<Tshirt> findByColor(String color);

    /**
     * Method to find Tshirts by size
     * @param size used to get all the Tshirts of the same size.
     * @return Returns a List of Tshirts matching the given size.
     */
    List<Tshirt> findBySize(String size);
}
