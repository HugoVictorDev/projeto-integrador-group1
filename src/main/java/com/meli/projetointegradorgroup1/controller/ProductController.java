package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.ProductDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;


    // cadastrar novo produto
    @PostMapping("/create")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO){
        productService.validaProduct(productDTO);
        Product product = ProductDTO.converte(productDTO);
        return ProductDTO.converte(productRepository.save(product));
    }

    // listar todos os produtos
    @GetMapping("/list")
    public List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList;
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
