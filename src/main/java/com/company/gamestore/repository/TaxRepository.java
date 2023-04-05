package com.company.gamestore.repository;

import com.company.gamestore.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TaxRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "tax" table.
 */
@Repository
public interface TaxRepository extends JpaRepository<Tax, String> {

}
