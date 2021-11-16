package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.ProductRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.ProductResponseDto;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // pegando lista de produtos, iterando e trazendo em formato de dto
    public List<ProductResponseDto> listProductDto(){
        if (productRepository.findAll().size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não encontramos produtos para essa pesquisa.");
        } else {
            return productRepository.findAll()
                    .stream()
                    .map(ProductResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    public List<ProductResponseDto> listProductDto(String nameId){
        if (productRepository.findByNameContaining(nameId).size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não encontramos produtos para essa pesquisa.");
        } else {
            return productRepository.findByNameContaining(nameId)
                    .stream()
                    .map(ProductResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    public ProductRequestDTO save(ProductRequestDTO productRequestDTO){
        productRepository.save(productRequestDTO.converte(productRequestDTO));
        return productRequestDTO;
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

    public ProductResponseDto findById(Long id){
        return listProductDto().stream()
                .filter(productResponseDto -> productResponseDto.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não encontramos produto com esse Id."));
    }

    public Product validaUpdate(Optional<Product> productFind, ProductRequestDTO productRequestDto){
        if (productFind.isPresent()){
            Product newProduct = productFind.get();
            newProduct.setName(productRequestDto.getName());
            newProduct.setDescription(productRequestDto.getDescription());
            return newProduct;
        } else {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "Não encontramos produto com esse Id para ser atualizado.");
        }
    }

    public ProductRequestDTO convertEntityToDtoRequest(Product product){
        ProductRequestDTO productRequestDto = new ProductRequestDTO();
        productRequestDto.setName(product.getName());
        productRequestDto.setDescription(product.getDescription());
        return productRequestDto;
    }
}