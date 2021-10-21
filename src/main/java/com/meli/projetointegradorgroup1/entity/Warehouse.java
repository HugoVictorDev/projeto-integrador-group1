package com.meli.projetointegradorgroup1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouseid")
    private Long warehouseId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "size")
    private String size;

    public Warehouse() {
    }

    public Warehouse(String name, String address, String size) {
        this.name = name;
        this.address = address;
        this.size = size;
    }
}
