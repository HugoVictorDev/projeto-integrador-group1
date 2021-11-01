package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    // depois ver a validacao de mínimo de caracter pq nao está validando
    @Pattern(regexp="^[a-zA-Z]+(?:\\s[a-zA-Z]+)?${3,50}",message="Apenas letras, mínimo 3 caracteres")
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String productName;

    private String description;

    public Product convert(){
        Product product = new Product()
                .setProductName(this.productName)
                .setDescription(this.description);

        return product;
    }
}