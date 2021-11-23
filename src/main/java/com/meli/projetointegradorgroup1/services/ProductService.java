package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    public List<ProductResponseDTO> listProductAll(){
        List <Product> list = productRepository.findAll();
            if(list.size() != 0) {
               return list
                        .stream()
                        .map(ProductResponseDTO::new)
                        .collect(Collectors.toList());
            }else {
                throw new RuntimeException("Não existem produtos cadastrados");
            }
    }

    public List<ProductResponseDTO> listProduct(String name){
        List<Product> list = productRepository.findByNameContaining(name);
        if(list.size() != 0) {
            return list
                    .stream()
                    .map(ProductResponseDTO::new)
                    .collect(Collectors.toList());
        }else {
            throw new RuntimeException("Produto não encontrado");
        }
    }


    public void valida(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new RuntimeException("Produto não cadastrado");
        }
    }


    public Product validaUpdate(Product productFind, ProductRequestDTO productRequestDto){
        if (productFind.getId() == null) {
            throw new RuntimeException("Produto não encontrado");
        }else{
            Product newProduct = productFind;
            newProduct.setName(productRequestDto.getName());
            newProduct.setDescription(productRequestDto.getDescription());
            return newProduct;
        }
    }

    public Product convert(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .stockType(dto.getStockType())
                .build();
    }

    public ProductResponseDTO convertToDto(Product product) {
        return ProductResponseDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stockType(product.getStockType())
                .build();
    }

    public ResponseEntity<Object> save(Product product , UriComponentsBuilder uriBuilder){
        try {
            productRepository.save(product);
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RuntimeException("Erro na gravação do produto:",e));
        }
        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity
                .created(uri).body(convertToDto(product));
    }


    public Product obtem(Long id){
        Optional<Product> byId = productRepository.findById(id);
        if(byId == null || byId.equals(Optional.empty()) ){
            throw new RuntimeException("Produto não cadastrado");
        }else {
            return byId.get();
        }
    }

    public void deletaProduct(Long id) {
        try{
            productRepository.deleteById(id);
        } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("Referential integrity constraint violation")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }
}