package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchStockItemRepository extends JpaRepository<BatchStockItem, Long> {


}
