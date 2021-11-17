package com.meli.projetointegradorgroup1.dto;
import com.meli.projetointegradorgroup1.entity.BatchStock;
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
public class BatchStockDTO {

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

}
