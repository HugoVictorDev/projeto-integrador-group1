package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatchStockRepository  extends JpaRepository<BatchStock, Long> {
    Optional<BatchStock> deleteBybatchStockNumber(Long OrderNumber);
    Optional<BatchStock> findById(Long Id);

    List<BatchStock> findAll();


}
