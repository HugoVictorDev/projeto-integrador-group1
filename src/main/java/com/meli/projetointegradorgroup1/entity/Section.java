package com.meli.projetointegradorgroup1.entity;

import com.meli.projetointegradorgroup1.dto.WarehouseDTOHugo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "minimum_temperature")
    private String minimumTemperature;
    @Column(name = "stock")
    private String stock;
    @Column(name = "stock_type")
    private String stockType;



    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


}
