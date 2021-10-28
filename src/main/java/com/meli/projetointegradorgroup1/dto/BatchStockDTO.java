
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@Data
//conjunto de lote
public class BatchStockDTO {


    private Long batchStockNumber;

    @NotNull(message = "currentTemprature é obrigatorio")
    private Long currentTemperature;

    @NotNull(message = "minimumTemprature é obrigatorio")
    private Long minimumTemperature;

    @NotBlank(message = "initialQuality é obrigatorio")
    private String initialQuality;

    @NotNull(message = "minimumTemprature é obrigatorio")
    private Long batchStockItem;

    @NotBlank(message = "currentQuality é obrigatorio")
    private String currentQuality;

    private String manufacturingTime;
    private String dueDate;
    private Long productID;


    public static BatchStock converte(BatchStockDTO batchStockdto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new BatchStock().CurrentTemperature(batchStockdto.getCurrentTemperature())
                                .MinimumTemperature(batchStockdto.getMinimumTemperature())
                                .InitialQuality(batchStockdto.getInitialQuality())
                                .CurrentQuality(batchStockdto.getCurrentQuality())
                                .ManufacturingTime(LocalDateTime.parse(batchStockdto.getManufacturingTime(),formatter))
                                .DueDate(LocalDate.parse(batchStockdto.getDueDate()))
                                .ProductID(batchStockdto.getBatchStockItem());
    }

}



