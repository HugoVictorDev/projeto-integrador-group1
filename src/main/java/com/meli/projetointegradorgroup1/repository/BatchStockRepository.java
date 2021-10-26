package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.InBoundOrder;

import java.util.Optional;

public interface BatchStockRepository {
    Optional<InBoundOrder> deleteByOrderNumber(Long OrderNumber);
    Optional<InBoundOrder> findById(Long Id);

}
