package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
//ordem de entrada
public class InBoundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderNumber", nullable = false)
    private Long orderNumber;

    @ManyToOne
    private Representative representative;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_code")
    private Section section;


    @OneToMany(cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;




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
