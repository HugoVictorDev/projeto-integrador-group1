package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "O campo nome nao pode estar vazio")
    private String name;

    private String description;

    public Product converte(ProductRequestDTO dto){
        return  Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
