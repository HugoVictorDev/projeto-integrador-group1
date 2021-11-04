package com.meli.projetointegradorgroup1.repository;


import com.meli.projetointegradorgroup1.entity.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SellerRepository extends JpaRepository<Seller, Long> {


    Optional<Seller> findBySellerId(Long sellerId);
}
