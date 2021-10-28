package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Accessors(chain = true) //true todo o set retorna o proprio objeto
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @NotBlank
    @Size(min = 3, message = "minimo 3 letras")
    @Column(name = "name")
    private String name;
    @CPF
    @Column(name = "cpf")
    private String cpf;
    @Email
    @Column(name = "email")
    private String email;

}
