
package com.meli.projetointegradorgroup1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
@Accessors(chain = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "batchstock")
//conjunto de lote
public class BatchStock {


    //numero do lote
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchNumber")
    private Long batchStockNumber;
    // temperatura atual
    @Column(name = "currentTemprature")
    private Long currentTemprature;
    //temperatura minima
    @Column(name = "minimumTemprature")
    private Long minimumTemprature;
    //estado inicial da qualidade do produto
    @Column(name = "initialQuality")
    private String initialQuality;
    // estado atual da qualidade do produto
    @Column(name = "currentQuality")
    private String currentQuality;

    //    @Column(name = "batchStockItem")
    @OneToMany(mappedBy = "batchstock", cascade = CascadeType.ALL)
    private List<BatchStockItem> batchStockItem;


    @ManyToOne
    @JoinColumn(name = "inboundorder_order_number")
    private InBoundOrder inboundorder;

    public BatchStock(Long batchStockNumber, Long currentTemprature, Long minimumTemprature, String initialQuality, String currentQuality, List<BatchStockItem> batchStockItem, InBoundOrder inboundorder) {
        this.batchStockNumber = batchStockNumber;
        this.currentTemprature = currentTemprature;
        this.minimumTemprature = minimumTemprature;
        this.initialQuality = initialQuality;
        this.currentQuality = currentQuality;
        this.batchStockItem = batchStockItem;
        this.inboundorder = inboundorder;
    }

    public InBoundOrder getInboundorder() {
        return inboundorder;
    }

    public void setInboundorder(InBoundOrder inboundorder) {
        this.inboundorder = inboundorder;
    }
}