package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@Entity
@Data
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SECTION_ID")
    private Long sectionId;

    @Column(name = "MINIMUM_TEMPRATURE")
    private String minimumTemprature;
    @Column(name = "STOCK")
    private String stock;
    @Column(name = "STOCK_TYPE")
    private String stockType;
    @Column(name = "BATCH_STOCK")
    private String batchStock;

    @ManyToOne
    @JoinColumn(name = "WAREHOUSE_ID")
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
