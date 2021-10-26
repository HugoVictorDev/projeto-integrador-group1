package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Entity
@Data
@Table(name = "product")

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
   // @Column(name = "seller_id")
    private Seller seller = new Seller();

    @ManyToOne
   // @Column(name = "batchstockitem_id")
    private BatchStockItem batchstockitem = new BatchStockItem();

    public Product() {

    }

    public Product withProductName(String productName){
        this.productName = productName;
        return this;
    }

    public Product withManufacturingDate(String manufacturingDate){
        this.manufacturingDate = manufacturingDate;
        return this;
    }

    public Product withManufacturingTime(String manufacturingTime){
        this.manufacturingTime = manufacturingTime;
        return this;
    }

    public Product withDueDate(String dueDate){
        this.dueDate = dueDate;
        return this;
    }

    public Product SellerId(Long sellerId){
        this.seller.setSellerId(sellerId);
        return this;
    }

    public Product BatchStockItemId(Long batchstockitemId){
        this.batchstockitem.setId(batchstockitemId);
        return this;
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
