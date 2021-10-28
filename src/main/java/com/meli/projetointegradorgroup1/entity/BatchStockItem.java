package com.meli.projetointegradorgroup1.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.meli.projetointegradorgroup1.dto.SectionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "sellerid")
    private Seller seller;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "productid")
    private Product product;

    private String sellerIdNum;

    @ManyToOne
    @JoinColumn(name = "batchstock_batchNumber")
    private BatchStock batchstock;


}
