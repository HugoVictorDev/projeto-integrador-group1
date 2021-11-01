package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDto;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ProductRequestDto createProductDto(@Valid @RequestBody ProductRequestDto productRequestDto){
        this.productRepository.save(productRequestDto.convert());
        return productRequestDto;
    }

    // listar todos os produtos
    @GetMapping("/list")
    public List<ProductResponseDto> responseDtoList(){
        return productService.listProductDto();
    }

    // buscar produto por id
//    @GetMapping("/id/{id}")
//    public ProductResponseDto getById(@PathVariable("id") Long id){
//        return productService.productDtoById(productRepository.getById(id));
//    }


    @GetMapping("{id}")
    public ProductResponseDto getById(@PathVariable("id") Long id) {
        Optional<Product> productFind = productRepository.findById(id);
        return ProductResponseDto.convertDto(productService.findProduct(productFind));
    }

    // buscar produto por nome
    @GetMapping("/list/{productName}")
    public List<ProductResponseDto> getByName(@PathVariable String productName){
        return productService.listProductDto(productName);
    }

    // atualizar produto por id
    @PutMapping("/update/{id}")
    public ProductRequestDto updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductRequestDto productRequestDto){

        Optional<Product> productFind = productRepository.findById(id);
        Product newProduct = productService.validaUpdate(productFind, productRequestDto);
        return productService.convertEntityToDtoRequest(productRepository.save(newProduct));
    }

    // deletar produto por id
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return "Produto de id " + id + " excluído com sucesso!";
    }

}
