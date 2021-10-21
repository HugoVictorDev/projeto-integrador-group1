package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    //data de producao
    @Column(name = "MANUFACTURING_DATE")
    private String manufacturingDate;
    //horario da producao
    @Column(name = "MANUFACTURING_TIME")
    private String manufacturingTime;
    //data de vencimento
    @Column(name = "DUE_DATE")
    private String dueDate;


}
