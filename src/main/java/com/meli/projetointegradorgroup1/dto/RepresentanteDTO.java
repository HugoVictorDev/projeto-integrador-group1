package com.meli.projetointegradorgroup1.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
@Builder
@Service
@Data
public class RepresentanteDTO {

    private Long representatne_Id;

    @NotBlank(message = "Campo Name é obrigatorio")
    @Pattern(regexp="^[a-zA-Z]+(?:\\s[a-zA-Z]+)?${3,50}",message="Apenas letras, minimo 3 caracteres")
    private String name;

    @NotBlank(message = "Campo CPF é obrigatorio")
    @Pattern(regexp="^[0-9]{11}",message = "Deve conter exatamente 11 digitos e apenas numeros, ")
    private String cpf;

    public RepresentanteDTO() {
    }
    
       public RepresentanteDTO(Long representatne_Id, String name, String cpf) {
        this.representatne_Id = representatne_Id;
        this.name = name;
        this.cpf = cpf;
    }


}