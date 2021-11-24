package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String description;
    @Enumerated(value = EnumType.STRING)
    private StockType stockType;

}
