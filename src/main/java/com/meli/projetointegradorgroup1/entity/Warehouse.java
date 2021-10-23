package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.persistence.*;

@Configuration
@Entity
@Data
@Table(name = "warehouse")
public class Warehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAREHOUSE_ID")
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

    public Warehouse(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
