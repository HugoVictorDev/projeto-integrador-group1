package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


//item do lote
public class BatchStockItem {


    private Product product;
    private int quantity;
    private BatchStock batchStock;

}
