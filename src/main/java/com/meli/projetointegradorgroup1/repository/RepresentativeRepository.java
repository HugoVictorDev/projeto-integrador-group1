package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long>{
    Representative findAllByCpfAndWarehouse_WarehouseId(String cpf, Long warehouseID);
}
