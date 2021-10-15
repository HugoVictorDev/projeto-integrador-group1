package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//ordem de entrada
public class InbounderOrder {

    public String orderNumber;

    public String orderDate;

    public Section section;

    //lista de lote de estoque
    public List<BatchStoch> batchStock;

}
