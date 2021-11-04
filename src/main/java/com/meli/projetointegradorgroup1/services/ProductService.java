package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDto;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // pegando lista de todos os produtos, iterando e trazendo em formato de dto
    public List<ProductResponseDto> listProductDto(){

        if (productRepository.findAll().size() == 0){
            throw new RuntimeException("Não encontramos produtos cadastrados.");
        } else {
            return productRepository.findAll()
                    .stream()
                    .map(ProductResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    // lista de produtos por nome
    public List<ProductResponseDto> listProductDto(String nameId){

        if (productRepository.findByProductNameContaining(nameId).size() == 0){
            throw new RuntimeException("Não localizamos produto cadastrado com esse nome.");
        } else {

            return productRepository.findByProductNameContaining(nameId)
                    .stream()
                    .map(ProductResponseDto::new)
                    .collect(Collectors.toList());
        }
    }


    public void valida(Long productId) {
        Product product =  productRepository.findByProductId(productId);
        if (product == null){
            throw new RuntimeException("Produto não cadastrado.");
        }
    }

    // localizando produto por id
//    public ProductResponseDto productDtoById(Product product){
//
//        ProductResponseDto productResponseDto = new ProductResponseDto();
//        productResponseDto.setProductName(product.getProductName());
//        productResponseDto.setDescription(product.getDescription());
//        return productResponseDto;
//
//    }

    // teste
    public Product findProduct(Optional<Product> productFind) {
        if (productFind.get().getProductId() == null){
            throw new RuntimeException("Não localizamos produto com esse Id.");
        }else{
            Product product = productFind.get();
            return product;
        }
    }

    // validando atualizaçao de produto por id
    public Product validaUpdate(Optional<Product> productFind, ProductRequestDto productRequestDto){
        if (productFind.isPresent()){
            Product newProduct = productFind.get();
            newProduct.setProductName(productRequestDto.getProductName());
            newProduct.setDescription(productRequestDto.getDescription());

            return newProduct;
        } else {
            throw new RuntimeException("Não localizamos produto com esse Id para ser atualizado.");
        }
    }

    public ProductRequestDto convertEntityToDtoRequest(Product product){
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setProductName(product.getProductName());
        productRequestDto.setDescription(product.getDescription());
        return productRequestDto;
    }

}