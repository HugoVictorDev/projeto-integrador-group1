package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findBywarehouseId(Long warehouseID);
}