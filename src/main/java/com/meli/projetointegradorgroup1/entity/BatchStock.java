
package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//conjunto de lote
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchNumber")
    private Long batchStockNumber;

    @Column(name = "currentTemperature")
    private Long currentTemperature;

    @Column(name = "minimumTemeprature")
    private Long minimumTemperature;

    @Column(name = "initialQuality")
    private String initialQuality;

    @Column(name = "currentQuality")
    private String currentQuality;

    @Column(name = "manufacturingTime")
    private LocalDateTime manufacturingTime;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    private Long productID;

    @OneToMany(mappedBy = "batchstock")
    private List<BatchStockItem> batchStockItem;

    @ManyToOne
    @JoinColumn(name = "inboundorder_orderNumber")
    private InBoundOrder inboundorder;


    public BatchStock(Long currentTemperature, Long minimumTemperature, String initialQuality, String currentQuality, String manufacturingDate, String manufacturingTime, String due_date, Long product_ID) {
    }

    public void setInboundorder(InBoundOrder inboundorder) {
        this.inboundorder = inboundorder;
    }

    public BatchStock BatchStockNumber(Long batchStockNumber) {
        this.batchStockNumber = batchStockNumber;
        return this;
    }

    public BatchStock CurrentTemperature(Long currentTemperature) {
        this.currentTemperature = currentTemperature;
        return this;
    }

    public BatchStock MinimumTemperature(Long minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
        return this;
    }

    public BatchStock InitialQuality(String initialQuality) {
        this.initialQuality = initialQuality;
        return this;
    }

    public BatchStock CurrentQuality(String currentQuality) {
        this.currentQuality = currentQuality;
        return this;
    }

    public BatchStock ManufacturingTime(LocalDateTime manufacturingTime) {
        this.manufacturingTime = manufacturingTime;
        return this;
    }

    public BatchStock DueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public BatchStock ProductID(Long productID) {
        this.productID = productID;
        return this;
    }

}