package com.meli.projetointegradorgroup1.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class Representative {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPRESENTATIVE_ID")
    private Long representative_Id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CPF")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse = new Warehouse();

    public Representative() {
    }

    public Representative Name(String name) {
        this.name = name;
        return this;
    }

    public Representative Cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Representative WarehouseID(Long warehouseID) {
        this.warehouse.setWarehouseId(warehouseID);
        return this;
    }
}