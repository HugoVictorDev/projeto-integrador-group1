package com.meli.projetointegradorgroup1.entity;

import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
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
    private Warehouse warehouse = new Warehouse();

    public Section() {
    }

    public Section(Long sectionId, String minimumTemperature, String stock, String stockType, Warehouse warehouse) {
        this.sectionId = sectionId;
        this.minimumTemperature = minimumTemperature;
        this.stock = stock;
        this.stockType = stockType;
        this.warehouse = warehouse;
    }

    public Section MinimumTemprature(String minimumTemperature){
        this.minimumTemperature = minimumTemperature;
        return this;
    }

    public Section Stock(String stock){
        this.stock = stock;
        return this;
    }

    public Section StockType(String stockType){
        this.stockType = stockType;
        return this;
    }


    public Section(Long sectionId, Warehouse warehouse) {
        this.sectionId = sectionId;
        this.warehouse = warehouse;
    }

    public Section WarehouseID(Long warehouseID){
       this.warehouse.setWarehouseId(warehouseID);
       return this;
    }
}
