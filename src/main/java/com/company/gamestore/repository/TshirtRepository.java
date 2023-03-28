package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
}
