package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Table
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String productId;

    private String productName;


    @ManyToOne
    private Seller seller;

    @ManyToOne
    private BatchStockItem batchstockitem;


}
