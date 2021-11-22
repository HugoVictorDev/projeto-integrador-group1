package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
//ordem de entrada
public class InBoundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "orderNumber", nullable = false)
    private Long orderNumber;

    private LocalDate orderDate;

    @OneToOne
    private Section section;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name= "inbound_order_id", nullable = false)
    private List<BatchStock> batchStock;

    @ManyToOne
    private Representante representative;


}
