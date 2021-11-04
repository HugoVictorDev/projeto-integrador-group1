package com.meli.projetointegradorgroup1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;

import com.meli.projetointegradorgroup1.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDTOhugo {

    private Long batchStockNumber;
    private Long batchStockItem;
    private double currentTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
    private String initialQuality;
    private String currentQuality;
    private String  manufacturingTime;
    private LocalDate dueDate;
    private int quantity; //quantidade de produtos chegando no lote
    private double volume; //volume total ocupado pelo lote de produtos

    public static BatchStock convertedto( BatchStockDTOhugo dto, BatchStockItem batchStockItem){
        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(BatchStockItem.builder().id(dto.batchStockItem).build())
                .currentTemperature(dto.getCurrentTemperature())
                .minimumTemperature(dto.getMinimumTemperature())
                .initialQuality(dto.getInitialQuality())
                .currentQuality(dto.getCurrentQuality())
                .manufacturingTime(null)
                .dueDate(dto.getDueDate()).build();




    }
}
