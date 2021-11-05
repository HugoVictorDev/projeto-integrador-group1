package com.meli.projetointegradorgroup1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import javax.persistence.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BatchStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double volume;
    private double maximumTemperature;
    private double minimumTemperature;

    @ManyToOne
    private Product product;

    @OneToOne
    private BatchStock batchStock;
}


