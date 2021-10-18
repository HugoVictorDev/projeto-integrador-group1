package com.meli.projetointegradorgroup1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {

    @Id
    private String productId;
    private String productName;

    //data de producao
    private String manufacturingDate;
    //horario da producao
    private String manufacturingTime;
    //data de vencimento
    private String dueDate;


}
