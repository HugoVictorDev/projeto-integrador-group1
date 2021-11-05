package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "O campo nome nao pode estar vazio")
    private String productName;

    private String description;

    public Product build(){
        Product product = new Product()
                .setName(this.productName)
                .setDescription(this.description);

        return product;
    }
}
