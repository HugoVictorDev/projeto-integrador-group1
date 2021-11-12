package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BatchStockResponseDTO {


    BatchStockItemService batchStockItemService;

    private Long batchStockNumber;
    private double currentTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
    private String initialQuality;
    private String currentQuality;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private int quantity;
    private double volume;
    private Long productID;

    private BatchStockItem batchStockItem;






    public BatchStockResponseDTO converte(BatchStock batchStock) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return BatchStockResponseDTO.builder()
                .batchStockNumber(batchStock.getBatchStockNumber())
                .batchStockItem(batchStockItemService.getBatchStockItem(batchStock.getBatchStockNumber()))
                .currentTemperature(batchStock.getCurrentTemperature())
                .minimumTemperature(batchStock.getMinimumTemperature())
                .maximumTemperature(batchStock.getMaximumTemperature())
                .quantity(batchStock.getQuantity())
                .volume(batchStock.getVolume())
                .initialQuality(batchStock.getInitialQuality())
                .currentQuality(batchStock.getCurrentQuality())
                .manufacturingTime(batchStock.getManufacturingTime())
                .dueDate(batchStock.getDueDate())
                .build();
    }

}
