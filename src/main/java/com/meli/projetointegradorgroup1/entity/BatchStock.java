package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
@Table(name = "batchstock")
//conjunto de lote
public class BatchStock {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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
