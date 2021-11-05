package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;

import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockRequestDTO {

    private Long batchStockNumber;
    private Long batchStockItem;
    private Long sellerId; // eu inseri TODO
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
        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(batchStockItemService.obtem(dto.batchStockItem))
                .currentTemperature(dto.getCurrentTemperature())
                .minimumTemperature(dto.getMinimumTemperature())
                .initialQuality(dto.getInitialQuality())
                .currentQuality(dto.getCurrentQuality())
                .manufacturingTime(null)
                .dueDate(dto.getDueDate())
                .seller(sellerService.obter(dto.getSellerId())) // EU inseri TODO
                .build();
        

    }
}
