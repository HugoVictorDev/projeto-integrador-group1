package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SellerRepository extends JpaRepository<Seller, Long> {

    List<Seller> findByNameContaining(String name);
}