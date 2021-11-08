package com.meli.projetointegradorgroup1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;


@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long code;

    @Column(name = "minimum_temperature")
    private String minimumTemperature;
    private String stock;
    @Column(name = "stock_type")
    private String stockType;

    @ManyToOne
    private Warehouse warehouse;




}
