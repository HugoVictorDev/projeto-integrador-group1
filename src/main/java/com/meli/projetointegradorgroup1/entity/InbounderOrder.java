package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "inbounderorder")
//ordem de entrada
public class InbounderOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderNumber", nullable = false)
    private String orderNumber;

    @OneToOne
    @JoinColumn(name = "representative")
    private Representative representative;

    @Column(name = "orderDate")
    private String orderDate;
//    private Section section;

    //lista de lote de estoque
    //@OneToMany(mappedBy = "InbounderOrder", cascade = CascadeType.ALL)

//    private List<BatchStock> batchStock;

    public InbounderOrder() {
    }


}
