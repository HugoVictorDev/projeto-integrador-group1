package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Product {

    @Id
    private String product_Id;
    private String product_Name;
    //data de producao
    private String manufacturing_Date;
    //horario da producao
    private String manufacturing_Time;
    //data de vencimento
    private String due_Date;


}
