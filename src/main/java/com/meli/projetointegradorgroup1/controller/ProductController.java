package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    // cadastrar novo produto
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        try {
                Product newProduct = productRepository.save(new Product(product.getProductName(), product.getManufacturingDate(), product.getManufacturingTime(), product.getDueDate()));
                return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // listar todos os produtos
    @GetMapping("/list")
    public Iterable<Product> list(){
        return productRepository.findAll();
    }

    // buscar produto por id
    @GetMapping("/list/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Optional<Product> productFind = productRepository.findById(id);

        if (productFind.isPresent()){
            return new ResponseEntity<>(productFind.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        Optional<Product> productFind = productRepository.findById(id);

        if (productFind.isPresent()){
            Product newProduct = productFind.get();
            newProduct.setProductName(product.getProductName());
            newProduct.setManufacturingDate(product.getManufacturingDate());
            newProduct.setManufacturingTime(product.getManufacturingTime());
            newProduct.setDueDate(product.getDueDate());
            return new ResponseEntity<>(productRepository.save(newProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id){
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
