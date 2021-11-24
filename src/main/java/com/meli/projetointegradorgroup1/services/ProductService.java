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
/**
 * @author Patricia Souza
 * @author Hugo Victor
 * @author Marco Siqueiraa
 */

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
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

    /**
     * @author Patricia Souza
     */
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

    /**
     * @author Hugo Victor
     */
    public List<Product> listProductByType(String type){
        return productRepository.findAll()
                .stream().filter(product -> product.getStockType().equals(type))
                .collect(Collectors.toList());
    }

    /**
     * @author Hugo Victor
     */
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



    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
    public Product validaUpdate(Product productFind, ProductRequestDTO productRequestDto){
        if (productFind.getId() == null) {
            throw new RuntimeException("Produto não encontrado");
        }else{
            Product newProduct = productFind;
            newProduct.setName(productRequestDto.getName());
            newProduct.setDescription(productRequestDto.getDescription());
            newProduct.setStockType(productRequestDto.getStockType());
            return newProduct;
        }
    }

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
    public Product convert(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .stockType(dto.getStockType())
                .build();
    }

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
    public ProductResponseDTO convertToDto(Product product) {
        return ProductResponseDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stockType(product.getStockType())
                .build();
    }

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
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

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
    public Product obtem(Long id){
        Optional<Product> byId = productRepository.findById(id);
        if(byId == null || byId.equals(Optional.empty()) ){
            throw new RuntimeException("Produto não cadastrado");
        }else {
            return byId.get();
        }
    }

    /**
     * @author Patricia Souza
     * @author Marco Siqueiraa
     */
    public void deletaProduct(Long id) {
        try{
            productRepository.deleteById(id);
        } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("violates foreign key constraint")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }
}