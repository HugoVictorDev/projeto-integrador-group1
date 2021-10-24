package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "inboundorder")
//ordem de entrada
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderNumber", nullable = false)
    private Long orderNumber;

    @OneToOne
    @JoinColumn(name = "representative")
    private Representative representative;

    @Column(name = "orderDate")
    private String orderDate;
//    private Section section;

    //lista de lote de estoque
    @OneToMany(mappedBy = "inboundorder", cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;


    public InboundOrder(Long orderNumber, Representative representative, String orderDate) {
        this.orderNumber = orderNumber;
        this.representative = representative;
        this.orderDate = orderDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public String getOrderDate() {
        return orderDate;
    }


}
