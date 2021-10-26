package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RepresentativeRepository extends JpaRepository<Representative, Long> {


}
