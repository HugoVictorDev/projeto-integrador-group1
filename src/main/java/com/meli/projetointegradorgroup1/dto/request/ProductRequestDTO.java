package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "O campo nome não pode estar vazio")
    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private StockType stockType;

    public Product converte(ProductRequestDTO dto){
        return  Product.builder()
                .stockType(dto.getStockType())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
