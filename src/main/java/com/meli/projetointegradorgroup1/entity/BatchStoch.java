package com.meli.projetointegradorgroup1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//conjunto de produtos
public class BatchStoch {

    //numero do lote
    public String batchNumber;
    // temperatura atual
    public String currentTemprature;
    //temperatura minima
    public String minimumTemprature;
    //estado inicial da qualidade do produto
    public String initialQuality;
    // estado atual da qualidade do produto
    public String currentQuality;
    //data de producao
    public String manufacturingDate;
    //horario da producao
    public String manufacturingTime;
    //data de vencimento
    public String dueDate;
    private List<BatchStockItem> batchStockItems;

}
