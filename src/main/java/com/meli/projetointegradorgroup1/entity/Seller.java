package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
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

    public Seller(String name, String cpf, List<Product> productList) {
        this.name = name;
        this.cpf = cpf;
        this.productList = productList;
    }

    @Column(name = "productList")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> productList;

}