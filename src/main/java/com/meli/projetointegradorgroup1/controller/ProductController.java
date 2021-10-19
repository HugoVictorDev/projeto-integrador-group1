package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/listar")
    public Iterable<Product> list(){
        return productRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        try {
                Product newProduct = productRepository.save(new Product(product.getProductName(), product.getManufacturingDate(), product.getManufacturingTime(), product.getDueDate()));
                return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
