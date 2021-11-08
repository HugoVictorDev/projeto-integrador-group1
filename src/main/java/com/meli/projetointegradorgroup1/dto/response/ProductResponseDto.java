package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDto {

    private Long productId;
    private String productName;
    private String description;

    public ProductResponseDto(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
    }

    public static ProductResponseDto convertDto(Product product){
        return new ProductResponseDto(product.getProductId(), product.getProductName(), product.getDescription());
    }
}