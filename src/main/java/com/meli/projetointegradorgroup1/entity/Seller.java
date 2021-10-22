package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    @NotBlank
    @Size(min = 3, message = "minimo 3 letras")
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;

    public Seller(String name, String cpf, List<Product> productList) {
        this.name = name;
        this.cpf = cpf;
        this.productList = productList;
    }

//    @Column(name = "productList")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productList;


}
