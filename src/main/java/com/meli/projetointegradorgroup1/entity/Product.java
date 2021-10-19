package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;
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


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "batchstockitem_id")
    private BatchStockItem batchstockitem;

    public BatchStockItem getBatchstockitem() {
        return batchstockitem;
    }

    public void setBatchstockitem(BatchStockItem batchstockitem) {
        this.batchstockitem = batchstockitem;
    }
}
