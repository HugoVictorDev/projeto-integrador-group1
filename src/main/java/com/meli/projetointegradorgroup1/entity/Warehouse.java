package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Entity
@Data

public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

//    private Representative representative;

    @NotNull @Column(name = "name")
    private String name;

    @NotNull @Column(name = "address")
    private String address;

    @NotNull @Column(name = "size")
    private String size;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Section> section;

    public Warehouse() {
    }


    public Warehouse(Long warehouseId) {
        this.warehouseId = warehouseId;
    }


    public Warehouse Name(String name) {
        this.name = name;
        return this;
    }
    public Warehouse Address(String address) {
        this.address = address;
        return this;
    }
    public Warehouse Size(String size) {
        this.size = size;
        return this;
    }
}