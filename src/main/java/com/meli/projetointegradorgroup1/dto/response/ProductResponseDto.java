package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
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
    private StockType stockType;

    public ProductResponseDto(Product product) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.description = product.getDescription();
        this.stockType = product.getStockType();
    }

    public static ProductResponseDto convertDto(Product product){
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getStockType());
    }
}