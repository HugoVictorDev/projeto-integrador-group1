package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

//conjunto de lote
public class BatchStock {

    //numero do lote
    private String batchNumber;
    // temperatura atual
    private String currentTemprature;
    //temperatura minima
    private String minimumTemprature;
    //estado inicial da qualidade do produto
    private String initialQuality;
    // estado atual da qualidade do produto
    private String currentQuality;

//    @Column(name = "batchStockItems")
    @OneToMany(mappedBy = "batchstock", cascade = CascadeType.ALL)
    private List<BatchStockItem> batchStockItems;

}
