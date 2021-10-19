package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;




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




}
