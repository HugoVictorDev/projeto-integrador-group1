
package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Data
//conjunto de lote
public class BatchStockDTO {

    //numero do lote
    private Long batchStockNumber;
    // temperatura atual
    @NotBlank
    @NotNull
    @NotEmpty(message = "currentTemprature é obrigatorio")
    private Long currentTemprature;

    //temperatura minima
    @NotBlank @NotNull @NotEmpty(message = "minimumTemprature é obrigatorio")
    private Long minimumTemprature;

    //estado inicial da qualidade do produto
    @NotBlank @NotNull @NotEmpty(message = "initialQuality é obrigatorio")
    private String initialQuality;

    // estado atual da qualidade do produto
    @NotBlank @NotNull @NotEmpty(message = "currentQuality é obrigatorio")
    private String currentQuality;

    //    Itens de um Lote
    private List<BatchStockItem> batchStockItem;

    // estado atual da qualidade do produto
    //@NotBlank @NotNull @NotEmpty(message = "currentQuality é obrigatorio")
    private InBoundOrder inboundorder;

    public BatchStockDTO(Long batchStockNumber, Long currentTemprature, Long minimumTemprature, String initialQuality, String currentQuality, List<BatchStockItem> batchStockItem, InBoundOrder inboundorder) {
        this.batchStockNumber = batchStockNumber;
    }


    public InBoundOrder getInboundorder() {
        return inboundorder;
    }

    public void setInboundorder(InBoundOrder inboundorder) {
        this.inboundorder = inboundorder;
    }

    public BatchStock converte(BatchStockDTO dto){
        return new BatchStock(dto.getBatchStockNumber(), dto.getCurrentTemprature(), dto.getMinimumTemprature() , dto.getInitialQuality(), dto.getCurrentQuality(), dto.getBatchStockItem(), dto.getInboundorder()) ;
    }
    public static BatchStockDTO converte(BatchStock batchStock){
        return new BatchStockDTO(batchStock.getBatchStockNumber(), batchStock.getCurrentTemprature(), batchStock.getMinimumTemprature() , batchStock.getInitialQuality(), batchStock.getCurrentQuality(), batchStock.getBatchStockItem(), batchStock.getInboundorder()) ;
    }
}