package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "BATCHSTOCK")
//conjunto de lote
public class BatchStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "BATCHNUMBER")
    //numero do lote
    private String batchNumber;

    // temperatura atual
    @Column(name = "CURRENTTEMPERATURE")
    private String currentTemprature;

    //temperatura minima
    @Column(name = "MINIMUMTEMPERATURE")
    private String minimumTemprature;

    //estado inicial da qualidade do produto
    @Column(name = "INITIALQUALITY")
    private String initialQuality;

    // estado atual da qualidade do produto
    @Column(name = "CURRENTQUALITY")
    private String currentQuality;

    @ManyToMany()
    @JoinColumn(name = "REPRESENTATIVE_ID")
    private List<BatchStockItem> batchStockItems;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "batchstockitem_id")
    private BatchStockItem batchstockitem;

    public BatchStockItem getBatchstockitem() {
        return batchstockitem;
    }

    public void setBatchstockitem(BatchStockItem batchstockitem) {
        this.batchstockitem = batchstockitem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
