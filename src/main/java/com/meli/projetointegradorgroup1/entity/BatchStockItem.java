package com.meli.projetointegradorgroup1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    private int quantity;

    //    @Column(name = "productlist")
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
