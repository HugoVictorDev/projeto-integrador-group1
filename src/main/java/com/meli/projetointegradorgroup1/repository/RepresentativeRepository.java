package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long>{
    Representative findAllByCpfAndWarehouse_WarehouseId(String cpf, Long warehouseID);
}
