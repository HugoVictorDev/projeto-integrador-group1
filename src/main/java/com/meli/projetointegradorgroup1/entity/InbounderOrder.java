package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "INBOUNDER")
//ordem de entrada
public class InbounderOrder {

    @Id
    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @OneToOne
    @JoinColumn(name = "REPRESENTATIVE_ID")
    private Representative representative;

    @Column(name = "ORDER_DATE")
    private String orderDate;

  //  @Column(name = "SECTION")
  //  private Section section;

    //   lista de lote de estoque

    @OneToMany
    private List<BatchStock> batchStock;

    public InbounderOrder() {
    }


}
