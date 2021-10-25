package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate orderDate;
//    private Section section;

    //lista de lote de estoque
    //@OneToMany(mappedBy = "inboundorder", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "inboundorder", cascade = CascadeType.ALL)
    private BatchStock batchStock;



    public InboundOrder(Long orderNumber, Representative representative, BatchStock batchStock, LocalDate orderDate) {
        this.orderNumber = orderNumber;
        this.representative = representative;
        this.batchStock = batchStock;
        this.orderDate = orderDate;

    }


    public Long getOrderNumber() {
        return orderNumber;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }


}
