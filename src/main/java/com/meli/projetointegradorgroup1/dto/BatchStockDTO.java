
package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "batchstock")
//conjunto de lote
public class BatchStockDTO {

    //numero do lote
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchNumber")
    private Long batchNumber;
    // temperatura atual
    @Column(name = "currentTemprature")
    private Long currentTemprature;
    //temperatura minima
    @Column(name = "minimumTemprature")
    private Long minimumTemprature;
    //estado inicial da qualidade do produto
    @Column(name = "initialQuality")
    private Long initialQuality;
    // estado atual da qualidade do produto
    @Column(name = "currentQuality")
    private String currentQuality;

    //    @Column(name = "batchStockItems")
    @OneToMany(mappedBy = "batchstock", cascade = CascadeType.ALL)
    private List<BatchStockItem> batchStockItems;


    @ManyToOne
    @JoinColumn(name = "inboundorder_order_number")
    private InBoundOrder inboundorder;

    public InBoundOrder getInboundorder() {
        return inboundorder;
    }

    public void setInboundorder(InBoundOrder inboundorder) {
        this.inboundorder = inboundorder;
    }
}