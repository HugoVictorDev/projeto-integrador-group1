package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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

    @ManyToOne // varias inbourderorder pode ter uma section
    @JoinColumn(name = "section_id") //referenciando a coluna dection_id
    private Section section;


    @OneToMany(cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;


}
