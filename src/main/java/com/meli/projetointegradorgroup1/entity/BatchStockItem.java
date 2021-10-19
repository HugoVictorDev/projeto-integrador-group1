package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BATCHSTOCKITEM")
//item do lote
public class BatchStockItem {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "batchstockitem")
    public Product product;
    @Column(name = "QUANTITY")
    private int quantity;

    @OneToOne(mappedBy = "batchstockitem")
    public BatchStock batchStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BatchStockItem() {
    }
}
