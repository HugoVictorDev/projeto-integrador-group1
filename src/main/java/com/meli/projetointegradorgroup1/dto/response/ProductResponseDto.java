package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDto {

    private String productName;
    private String description;

    public ProductResponseDto(Product product) {
        this.productName = product.getName();
        this.description = product.getDescription();
    }
}
