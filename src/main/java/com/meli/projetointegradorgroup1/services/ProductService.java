package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDto;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Seller;
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

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // pegando lista de produtos, iterando e trazendo em formato de dto
    public List<ProductResponseDto> listProductDto(){
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());

    }

    public List<ProductResponseDto> listProductDto(String nameId){
        return productRepository.findByProductNameContaining(nameId)
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }


    public void valida(Long productId) {
        Product product =  productRepository.findByProductId(productId);
        if (product == null){
            throw new RuntimeException("Produto não cadastrado");
        }
    }


    public ProductResponseDto productDtoById(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setDescription(product.getDescription());
        return productResponseDto;
    }

    public Product validaUpdate(Optional<Product> productFind, ProductRequestDto productRequestDto){
        if (productFind == null || productFind.equals(Optional.empty())){
            throw new RuntimeException("Produto não encontrado");
        } else {
            Product newProduct = productFind.get();
            newProduct.setProductName(productRequestDto.getProductName());
            newProduct.setDescription(productRequestDto.getDescription());
            return newProduct;
        }
    }

    public ProductRequestDto convertEntityToDtoRequest(Product product){
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setProductName(product.getProductName());
        productRequestDto.setDescription(product.getDescription());
        return productRequestDto;
    }

}