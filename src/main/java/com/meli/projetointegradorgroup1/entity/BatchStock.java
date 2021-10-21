package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
//conjunto de lote
public class BatchStock {

    //numero do lote
    @Id
    private String batchNumber;
    // temperatura atual
    private String currentTemprature;
    //temperatura minima
    private String minimumTemprature;
    //estado inicial da qualidade do produto
    private String initialQuality;
    // estado atual da qualidade do produto
    private String currentQuality;
 //   private List<BatchStockItem> batchStockItems;

}
