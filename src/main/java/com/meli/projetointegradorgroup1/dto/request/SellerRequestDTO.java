package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

//Essa classe nao conecta com BD, intermedio entre o usuario e a classe
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDTO {

    @NotBlank
    @Size(min = 3, message = "minimo 3 letras")
    private String name;

    @CPF
    private String cpf;

    @Email
    private String email;


     public Seller build(){
         Seller seller = new Seller()
                 .setName(this.name)
                 .setCpf(this.cpf)
                 .setEmail(this.email)
                 ;

        return seller;
    }


}
