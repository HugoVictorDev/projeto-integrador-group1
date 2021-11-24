package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatchStockItemRepository extends JpaRepository<BatchStockItem, Long> {
    Optional<BatchStockItem> findById(Long Id);
    List<BatchStockItem> findAll();


    BatchStockItem findByProduct_id(Long productId);
}
