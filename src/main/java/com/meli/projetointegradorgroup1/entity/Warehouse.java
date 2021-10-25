package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAREHOUSE_ID")
    private Long warehouseId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SIZE")
    private String size;



    public Warehouse() {
    }

    public Warehouse(String name, String address, String size) {
        this.name = name;
        this.address = address;
        this.size = size;
    }

    public Warehouse(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
