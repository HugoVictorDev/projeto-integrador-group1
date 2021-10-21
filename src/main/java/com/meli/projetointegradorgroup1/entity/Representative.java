package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

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

    @OneToOne(mappedBy = "representative")
    private InbounderOrder inbounderOrder;

    public Representative(Optional<Representative> byId) {
    }

}