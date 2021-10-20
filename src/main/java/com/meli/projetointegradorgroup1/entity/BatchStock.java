package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructory
@Entit
@Table(name = "batchstock")
//conjunto de lote
public class BatchStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //numero do lote
    @Column(name = "batchNumber")
    private String batchNumber;

    // temperatura atual
    @Column(name = "currentTemprature")
    private String currentTemprature;

    //temperatura minima
    @Column(name = "minimumTemprature")
    private String minimumTemprature;
    //estado inicial da qualidade do produto
    @Column(name = "initialQuality")
    private String initialQuality;

    // estado atual da qualidade do produto
    @Column(name = "CURRENTQUALITY")
    private String currentQuality;

//    @Column(name = "batchStockItems")
    @OneToMany(mappedBy = "batchstock", cascade = CascadeType.ALL)
    private List<BatchStockItem> batchStockItems;
}
