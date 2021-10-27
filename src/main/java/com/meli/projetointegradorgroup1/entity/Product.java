package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) // todo set retorna o próprio objeto
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productId;

    @NotBlank(message = "O campo nome nao pode estar vazio")
    @Column(name = "productname")
    private String productName;


    @Column(name = "description")
    private String description;

    public Product(String productName, String description) {
        this.productName = productName;
        this.description = description;
    }


    @ManyToOne
    private Seller seller;


    @ManyToOne
    private BatchStockItem batchstockitem;

}
