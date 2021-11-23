package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDTO {

    private String name;
    private String description;
    private StockType stockType;


    public ProductResponseDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.stockType = product.getStockType();
    }
}