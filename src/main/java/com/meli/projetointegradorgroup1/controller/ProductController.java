package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



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
    @ApiOperation(value = "Cadastrar novo produto")
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productRequestDto){
        Product product = productService.save(productService.converte(productRequestDto));
        return productService.converteToDto(product);
    }

    // listar todos os produtos
    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de produtos")
    public List<ProductResponseDTO> listProduct(){
        return productService.listProductAll();
    }

    // buscar produto por id
    @GetMapping("/id/{id}")
    @ApiOperation(value = "Retornar produto único a partir do id")
    public ProductResponseDTO getById(@PathVariable("id") Long id){
        return productService.converteToDto(productService.obtem(id));
    }

    // buscar produto por nome
    @GetMapping("/list/{productName}")
    @ApiOperation(value = "Retornar lista de produtos a partir do nome")
    public List<ProductResponseDTO> getByName(@PathVariable String productName){
        return productService.listProduct(productName);
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar produto a partir do id")
    public ProductResponseDTO updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductRequestDTO productRequestDto){
        Product productFind = productService.obtem(id);
        Product product = productService.validaUpdate(productFind, productRequestDto);
        return productService.converteToDto(productService.save(product));
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar produto a partir do id")
    public ProductResponseDTO deleteProduct(@PathVariable Long id){
        Product product  = productService.obtem(id);
        productService.deletaProduct(id);
        return productService.converteToDto(product);
    }

}
