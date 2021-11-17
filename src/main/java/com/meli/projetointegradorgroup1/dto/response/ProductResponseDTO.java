package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDTO {

    private String productName;
    private String description;

    public ProductResponseDTO(Product product) {
        this.productName = product.getName();
        this.description = product.getDescription();
    }
}