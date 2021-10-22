package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    //data de producao
    @Column(name = "MANUFACTURING_DATE")
    private String manufacturingDate;

    //horario da producao
    @Column(name = "MANUFACTURING_TIME")
    private String manufacturingTime;

    //data de vencimento
    @Column(name = "DUE_DATE")
    private String dueDate;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private BatchStockItem batchstockitem;

    public Product() {
    }

    public Product(String productName, String manufacturingDate, String manufacturingTime, String dueDate) {
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
    }

    public Product(Long productId, String productName, String manufacturingDate, String manufacturingTime, String dueDate, Seller seller, BatchStockItem batchstockitem) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
        this.seller = seller;
        this.batchstockitem = batchstockitem;
    }
}
