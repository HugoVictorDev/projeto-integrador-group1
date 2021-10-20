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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    private String productName;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private BatchStockItem batchstockitem;

    private String manufacturingDate;

    private String manufacturingTime;

    private String due_Date;


}
