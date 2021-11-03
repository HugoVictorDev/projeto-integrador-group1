package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDTOhugo {

    private Long batchStockNumber;
    private Long productID;
    private double currentTemperature;
    private double minimumTemperature;
    private String initialQuality;
    private String currentQuality;
    private String  manufacturingTime;
    private LocalDate dueDate;



    public BatchStock convertDTo () {
        return null;
        //TODO: Me dah medo
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        BatchStockItem batchStockItem = new BatchStockItem().setId(this.productID);
//        BatchStock batchStock = new BatchStock()
//                .setBatchStockNumber(this.batchStockNumber)
//                .setBatchStockItem(batchStockItem)
//                .setCurrentTemperature(this.currentTemperature)
//                .setMinimumTemperature(this.minimumTemperature)
//                .setInitialQuality(this.initialQuality)
//                .setCurrentQuality(this.currentQuality)
//                .setManufacturingTime(LocalDateTime.parse(this.manufacturingTime, formatter))
//                .setDueDate(this.dueDate);
//        return batchStock;

    }

    public static List<BatchStockDTOhugo> converterLista(List<BatchStock> loteList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        BatchStockItem batchStockItem = new BatchStockItem().setId(productID);

        List<BatchStockDTOhugo> loteDTOList = new ArrayList<>();
        for (BatchStock batchStock : loteList) {
            loteDTOList.add(BatchStockDTOhugo.builder()
                    .batchStockNumber(batchStock.getBatchStockNumber())
                            .currentQuality(batchStock.getCurrentQuality())
                            .currentTemperature(batchStock.getCurrentTemperature())
                            .minimumTemperature(batchStock.getMinimumTemperature())
                            .initialQuality(batchStock.getInitialQuality())
//                            .manufacturingTime(LocalDateTime.parse(batchStock.getManufacturingTime(), formatter))

                    .build());
        }
        return loteDTOList;
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
                                        .filter(item -> item.getProduct().getProductId().equals(dto.getProductID()))

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
