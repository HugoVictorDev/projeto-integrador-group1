
package com.meli.projetointegradorgroup1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Accessors(chain = true) //true todo o set retorna o proprio objeto
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
//conjunto de lote
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchNumber")
    private Long batchStockNumber;

    @Column(name = "currentTemperature")
    private double currentTemperature;

    @Column(name = "minimumTemeprature")
    private double minimumTemperature;

    @Column(name = "initialQuality")
    private String initialQuality;

    @Column(name = "currentQuality")
    private String currentQuality;

    @Column(name = "manufacturingTime")
    private LocalDateTime manufacturingTime;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    private Long productID;

    @ManyToOne
    private BatchStockItem batchStockItem;

//    @OneToMany(mappedBy = "batchstock")
//    private List<BatchStockItem> batchStockItem;

    @ManyToOne
    @JoinColumn(name = "inboundorder_orderNumber")
    private InBoundOrder inboundorder;

}