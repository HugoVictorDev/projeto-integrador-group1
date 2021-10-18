package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    //data de producao
    @Column(name = "manufacturing_date")
    private String manufacturingDate;

    @Column(name = "manufacturing_time")
    //horario da producao
    private String manufacturingTime;

    @Column(name = "due_date")
    //data de vencimento
    private String dueDate;

    public Product() {
    }

    public Product(String productName, String manufacturingDate, String manufacturingTime, String dueDate) {
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
    }
}
