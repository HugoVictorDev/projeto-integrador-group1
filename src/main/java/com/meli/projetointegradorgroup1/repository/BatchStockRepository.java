package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {
    List<BatchStock> findAll();
}
