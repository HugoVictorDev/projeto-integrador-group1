package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContaining(String productName);

    Product findByProductId(Long productId);

   // ProductResponseDto findByProductIdd(Long productId);

}
