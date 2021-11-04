package com.meli.projetointegradorgroup1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import javax.persistence.*;

import javax.validation.constraints.NotNull;


//item do lote // mini lote de apenas um produto
//@Accessors(chain = true) //true todo o set retorna o proprio objeto
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


