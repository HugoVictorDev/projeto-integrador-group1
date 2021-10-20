package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPRESENTATIVE_ID")
    private Long representative_Id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CPF")
    private String cpf;


    public Representative() {
    }

    public Representative(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }
}