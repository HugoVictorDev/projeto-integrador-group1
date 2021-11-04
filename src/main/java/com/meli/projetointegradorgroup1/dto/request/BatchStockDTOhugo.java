package com.meli.projetointegradorgroup1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDTOhugo {

    private Long batchStockNumber;
    @JsonProperty(namespace = "productId")
    private Long batchStockItem;
    private double currentTemperature;
    private double minimumTemperature;
    private String initialQuality;
    private String currentQuality;
    private String  manufacturingTime;
    private LocalDate dueDate;





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

    public static List<BatchStock> converte(List<BatchStockDTOhugo> dtos, List<BatchStockItem> listaDeStockItemsDoSellerDaInboundOrder){

        //TODO: esta lita nao pode chegar nula
        if(listaDeStockItemsDoSellerDaInboundOrder==null){
            return new ArrayList<BatchStock>();
        }

        List<BatchStock> resultList = dtos.stream()
                .map(dto -> BatchStock.builder()
                        .batchStockItem(
                                listaDeStockItemsDoSellerDaInboundOrder.stream()
                                        .filter(item -> item.getId().equals(dto.getBatchStockItem()))

                                        .map(i -> BatchStockItem.builder()
                                                .seller(i.getSeller())
                                                .quantity(i.getQuantity())
                                                .product(i.getProduct())
                                                .maximumTemperature(i.getMaximumTemperature())
                                                .volume(i.getVolume())
                                                .minimumTemperature(i.getMinimumTemperature())
                                                .build())
                                        .findFirst().get()
                        )

                        .batchStockNumber(dto.getBatchStockNumber())
                        .currentQuality(dto.getCurrentQuality())
                        .currentTemperature(dto.getCurrentTemperature())
                        .dueDate(dto.getDueDate())
                        .initialQuality(dto.getInitialQuality())
                        .manufacturingTime(null)
                        .build()
                ).collect(Collectors.toList());
        return resultList;
    }




}
