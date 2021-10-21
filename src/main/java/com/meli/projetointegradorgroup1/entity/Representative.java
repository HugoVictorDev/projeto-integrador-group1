package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





public class Representative {

    private Long id;

    private Long representativeId;
    private String name;
    private String cpf;




}
