package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // cadastrar novo produto
    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDto, UriComponentsBuilder uriBuilder) {
        Product product = productService.convert(productRequestDto);
        return productService.save(product,uriBuilder);
    }

    // listar todos os produtos
    @GetMapping("/list")
    public List<ProductResponseDTO> listProduct(){
        return productService.listProductAll();
    }

    // buscar produto por id
    @GetMapping("/id/{id}")
    public ProductResponseDTO getById(@PathVariable("id") Long id){
        return productService.convertToDto(productService.obtem(id));
    }

    // buscar produto por nome
    @GetMapping("/list/{productName}")
    public List<ProductResponseDTO> getByName(@PathVariable String productName){
        return productService.listProduct(productName);
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object>updateProduct(@PathVariable("id") Long id,@Valid @RequestBody ProductRequestDTO productRequestDto, UriComponentsBuilder uriBuilder) {
        Product productFind = productService.obtem(id);
        Product product = productService.validaUpdate(productFind, productRequestDto);
        return productService.save(product,uriBuilder);
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable Long id){
        Product product  = productService.obtem(id);
        productService.deletaProduct(id);
        return productService.convertToDto(product);
    }
}


