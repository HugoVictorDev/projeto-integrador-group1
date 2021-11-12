package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productRequestDto){
        Product product = productService.save(productService.converte(productRequestDto));
        return productService.converteToDto(product);
    }

    // listar todos os produtos
    @GetMapping("/list")
    public List<ProductResponseDTO> listProduct(){
        return productService.listProductAll();
    }

    // buscar produto por id
    @GetMapping("/id/{id}")
    public ProductResponseDTO getById(@PathVariable("id") Long id){
        return productService.converteToDto(productService.obtem(id));
    }

    // buscar produto por nome
    @GetMapping("/list/{productName}")
    public List<ProductResponseDTO> getByName(@PathVariable String productName){
        return productService.listProduct(productName);
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductRequestDTO productRequestDto){
        Product productFind = productService.obtem(id);
        Product product = productService.validaUpdate(productFind, productRequestDto);
        return productService.converteToDto(productService.save(product));
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable Long id){
        Product product  = productService.obtem(id);
        productService.deletaProduct(id);
        return productService.converteToDto(product);
    }
}


