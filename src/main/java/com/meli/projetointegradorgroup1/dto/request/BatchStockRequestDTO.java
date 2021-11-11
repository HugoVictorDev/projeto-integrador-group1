package com.meli.projetointegradorgroup1.dto.request;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class BatchStockRequestDTO {

    private Long batchStockNumber;
    private Long batchStockItem;
    private Long sellerId;
    private double currentTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
    private String initialQuality;
    private String currentQuality;
    private String  manufacturingTime;
    private LocalDate dueDate;
    private int quantity; //quantidade de produtos chegando no lote
    private double volume; //volume total ocupado pelo lote de produtos

    public static BatchStock convertedto(BatchStockRequestDTO dto,
                                         BatchStockItemService batchStockItemService, SellerService sellerService){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(batchStockItemService.obtem(dto.batchStockItem))
                .currentTemperature(dto.getCurrentTemperature())
                .minimumTemperature(dto.getMinimumTemperature())
                .maximumTemperature(dto.getMaximumTemperature())
                .quantity(dto.getQuantity())
                .volume(dto.getVolume())
                .initialQuality(dto.getInitialQuality())
                .currentQuality(dto.getCurrentQuality())
                .manufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(), fmt))
                .dueDate(dto.getDueDate())
                .seller(sellerService.findSellerById(dto.getSellerId()))
                .build();
        

    }
}
