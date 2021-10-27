package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
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

    @Column(name = "minimum_temprature")
    private String minimumTemprature;
    @Column(name = "stock")
    private String stock;
    @Column(name = "stock_type")
    private String stockType;
    @Column(name = "batch_stock")
    private String batchStock;



    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse = new Warehouse();

    public Section() {
    }

    public Section MinimumTemprature(String minimumTemprature){
        this.minimumTemprature = minimumTemprature;
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

    public Section BatchStock(String batchStock){
        this.batchStock = batchStock;
        return this;
    }

    public Section WarehouseID(Long warehouseID){
       this.warehouse.setWarehouseId(warehouseID);
       return this;
    }
}
