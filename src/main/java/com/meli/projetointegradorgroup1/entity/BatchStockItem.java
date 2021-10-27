package com.meli.projetointegradorgroup1.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//item do lote // mini lote de apenas um produto
@Accessors(chain = true) //true todo o set retorna o proprio objeto
@NoArgsConstructor
@Data
@Entity
public class BatchStockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private String sellerIds;


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "sellerid")
    private Seller seller;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "productid")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "batchstock_batchNumber")
    private BatchStock batchstock;


}
