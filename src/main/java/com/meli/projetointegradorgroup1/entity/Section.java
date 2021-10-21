package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


public class Section {

    @Id
    private String sectionCode;
    private Warehouse warehouse;

}
