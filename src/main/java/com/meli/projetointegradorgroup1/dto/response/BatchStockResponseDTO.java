package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BatchStockResponseDTO {

    private Long batchStockNumber;
    private double currentTemperature;
    private double minimumTemperature;
    private double initialQuality;
    private double currentQuality;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Long productID;




    public static BatchStockResponseDTO converte(BatchStock batchStock) {
        return null;
        //TODO: revisar
//        return new BatchStockResponseDTO(batchStock.getBatchStockNumber(),
//                batchStock.getCurrentTemperature(),
//                batchStock.getMinimumTemperature(),
//                batchStock.getInitialQuality(),
//                batchStock.getCurrentQuality(),
//                batchStock.getManufacturingTime(),
//                batchStock.getDueDate(),
//                batchStock.getProductID());
    }

}
