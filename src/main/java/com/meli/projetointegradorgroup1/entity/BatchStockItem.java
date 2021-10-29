package com.meli.projetointegradorgroup1.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import javax.persistence.*;

import javax.validation.constraints.NotNull;


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

   @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sellerId")
  private Seller seller;

   private Long sellerIdConvert;
   private Long productIdConvert;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "batchstock_batchNumber")
    private BatchStock batchstock;
}


