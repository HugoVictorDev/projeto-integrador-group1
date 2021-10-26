package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;
@Service
@Data
@Entity
@Table(name = "representative")
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPRESENTATIVE_ID")
    private Long representative_Id;

    @NotNull @Column(name = "NAME")
    private String name;
    @NotNull @Column(name = "CPF")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse = new Warehouse();

    public Representative() {
    }

    public Representative(Optional representative_Id) {
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

    public void setWarehouseID(long warehouseID) {
        this.warehouse.setWarehouseId(warehouseID);
    }
}