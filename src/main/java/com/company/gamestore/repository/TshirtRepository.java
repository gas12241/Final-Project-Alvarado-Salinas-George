package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
    List<Tshirt> findByColor();
    List<Tshirt> findBySize();
}
