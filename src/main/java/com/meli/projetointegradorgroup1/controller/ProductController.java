package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
/**
 * @author Patricia Souza
 */


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Cadastrar novo Produto")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDto, UriComponentsBuilder uriBuilder) {
        Product product = productService.convert(productRequestDto);
        return productService.save(product,uriBuilder);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de Produtos")
    public List<ProductResponseDTO> listProduct(){
        return productService.listProductAll();
    }


    @GetMapping("/id/{id}")
    @ApiOperation(value = "Retornar Produto único a partir do id")
    public ProductResponseDTO getById(@PathVariable("id") Long id){
        return productService.convertToDto(productService.obtem(id));
    }

    // buscar produto por nome
    @GetMapping("/list/{productName}")
    @ApiOperation(value = "Retornar Lista de Produtos a partir do nome")
    public List<ProductResponseDTO> getByName(@PathVariable String productName){
        return productService.listProduct(productName);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar Produto a partir do id")
    public ResponseEntity<Object>updateProduct(@PathVariable("id") Long id,@Valid @RequestBody ProductRequestDTO productRequestDto, UriComponentsBuilder uriBuilder) {
        Product productFind = productService.obtem(id);
        Product product = productService.validaUpdate(productFind, productRequestDto);
        return productService.save(product,uriBuilder);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar Produto a partir do id")
    public ProductResponseDTO deleteProduct(@PathVariable Long id){
        Product product  = productService.obtem(id);
        productService.deletaProduct(id);
        return productService.convertToDto(product);
    }
}


