package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true) //true todo o set retorna o proprio objeto
@NoArgsConstructor
@Data
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sellerId;
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;

    public Seller(String name, String cpf, String email, List<Product> productList) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.productList = productList;
    }

//    @Column(name = "productList")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productList;


}
