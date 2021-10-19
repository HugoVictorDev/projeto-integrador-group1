package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productId;

    @Column(name = "productname")
    private String productName;

    //data de producao
    @Column(name = "manufacturingdate")
    private String manufacturingDate;

    @Column(name = "manufacturingtime")
    //horario da producao
    private String manufacturingTime;

    @Column(name = "duedate")
    //data de vencimento
    private String dueDate;

    @ManyToOne
    private Seller seller;

    public Product() {
    }

    public Product(String productName, String manufacturingDate, String manufacturingTime, String dueDate) {
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
    }
}
