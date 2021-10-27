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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private int quantity;


    @OneToMany(mappedBy = "batchstockitem", cascade = CascadeType.ALL)
    private List<Product> productlist;


    @ManyToOne
    private BatchStock batchstock;

    public BatchStockItem(int quantity, List<Product> productlist, BatchStock batchstock) {
        this.quantity = quantity;
        this.productlist = productlist;
        this.batchstock = batchstock;

    }

}
