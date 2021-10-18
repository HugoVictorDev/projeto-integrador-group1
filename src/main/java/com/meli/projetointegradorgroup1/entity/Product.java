package com.meli.projetointegradorgroup1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;
    private String productName;

    //data de producao
    private String manufacturingDate;
    //horario da producao
    private String manufacturingTime;
    //data de vencimento
    private String dueDate;

    public Product(String productName, String dueDate) {
        this.productName = productName;
        this.dueDate = dueDate;
    }
}
