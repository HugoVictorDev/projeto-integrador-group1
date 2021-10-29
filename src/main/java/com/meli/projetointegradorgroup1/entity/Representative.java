package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;
@Service
@Data
@Entity
@NoArgsConstructor
public class Representative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "representative_id")
    private Long representative_Id;

    @NotNull @Column(name = "name")
    private String name;
    @NotNull @Column(name = "cpf")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse = new Warehouse();

    public Representative(Long representative_Id, String name, String cpf, Warehouse warehouse ) {
        this.representative_Id = representative_Id;
        this.name = name;
        this.cpf = cpf;
        this.warehouse = warehouse;
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

}