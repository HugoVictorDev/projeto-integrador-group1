package com.meli.projetointegradorgroup1.repository;
import com.meli.projetointegradorgroup1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author Patricia Souza
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String productName);

    Product findByid(Long id);
}
