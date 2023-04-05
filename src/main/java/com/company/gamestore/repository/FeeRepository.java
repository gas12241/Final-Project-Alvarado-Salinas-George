package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FeeRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "fee" table.
 */
@Repository
public interface FeeRepository extends JpaRepository<Fee, String> {
}
