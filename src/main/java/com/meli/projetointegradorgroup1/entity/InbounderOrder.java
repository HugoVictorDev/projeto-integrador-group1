package com.meli.projetointegradorgroup1.entity;

import java.util.List;

//ordem de entrada
public class InbounderOrder {

    private String orderNumber;
    private String orderDate;
    private Section section;
    //lista de lote de estoque
    private List<BatchStock> batchStock;

}
