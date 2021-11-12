package com.meli.projetointegradorgroup1.dto.request;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.SellerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockRequestDTO {

    BatchStockItemService batchStockItemService;
    Seller sellerService;
    SellerService sellerRepository;

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
    private Seller seller;


    public BatchStock convertedto(BatchStockRequestDTO dto){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(batchStockItemService.getBatchStockItem(dto.batchStockItem))
                .currentTemperature(dto.getCurrentTemperature())
                .minimumTemperature(dto.getMinimumTemperature())
                .maximumTemperature(dto.getMaximumTemperature())
                .initialQuality(dto.getInitialQuality())
                .currentQuality(dto.getCurrentQuality())
                .manufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(), fmt))
                .dueDate(dto.getDueDate())
                .quantity(dto.getQuantity())
                .volume(dto.getVolume())
                .seller(sellerRepository.findSellerById(dto.getSellerId()))
                .build();
        

    }
}
