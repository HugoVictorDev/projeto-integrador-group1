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

    @Column(name = "orderNumber", nullable = false)
    private Long orderNumber;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @OneToOne
    private Section section;

    @OneToMany(mappedBy = "inboundOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BatchStock> batchStock;

    @ManyToOne
    private Representante representative;


}
