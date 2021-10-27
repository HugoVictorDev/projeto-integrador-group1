package com.meli.projetointegradorgroup1.repository;


import com.meli.projetointegradorgroup1.entity.Seller;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findBySellerId(Long sellerId);

}
