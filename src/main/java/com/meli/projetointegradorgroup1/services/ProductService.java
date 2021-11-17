package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // pegando lista de produtos, iterando e trazendo em formato de dto
    public List<ProductResponseDto> listProductDto(){
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> listProductDto(String nameId){
        return productRepository.findByNameContaining(nameId)
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<Product> listProduct(String type){
        return productRepository.findAll()
                .stream().filter(product -> product.getStockType().equals(type))
                .collect(Collectors.toList());
    }

    public List<Long> listProductId(){
        return productRepository.findAll().stream()
                .map(product -> product.getId()).collect(Collectors.toList());
    }


    public void valida(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new RuntimeException("Produto não cadastrado");
        }
    }

    public Product obtem(Long id){
        Optional<Product> byId = this.productRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else {
        throw new RuntimeException("Produto não cadastrado");
        }
    }

    public ProductResponseDto productDtoById(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        return productResponseDto;
    }

    public Product validaUpdate(Optional<Product> productFind, ProductRequestDTO productRequestDto){
        if (productFind.isPresent()){
            Product newProduct = productFind.get();
            newProduct.setName(productRequestDto.getName());
            newProduct.setDescription(productRequestDto.getDescription());
            return newProduct;
        } else {
            throw new RuntimeException("Produto nao encontrado");
        }
    }

    public ProductRequestDTO convertEntityToDtoRequest(Product product){
        ProductRequestDTO productRequestDto = new ProductRequestDTO();
        productRequestDto.setName(product.getName());
        productRequestDto.setDescription(product.getDescription());
        return productRequestDto;
    }

}