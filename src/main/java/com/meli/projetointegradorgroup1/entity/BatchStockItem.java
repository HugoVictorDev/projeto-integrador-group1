package com.meli.projetointegradorgroup1.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//item do lote // mini lote de apenas um produto
@NoArgsConstructor
@Data
@Entity
@Table(name = "batchstockitem")
public class BatchStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Product product;

    private int quantity;

    @ManyToOne
    private BatchStock batchstock;

}
