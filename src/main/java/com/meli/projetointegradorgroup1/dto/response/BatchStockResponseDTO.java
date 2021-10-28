package com.meli.projetointegradorgroup1.dto.response;


import com.meli.projetointegradorgroup1.entity.BatchStock;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class BatchStockResponseDTO {

    private Long batchStockNumber;
    private Long currentTemperature;
    private Long minimumTemperature;
    private String initialQuality;
    private String currentQuality;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Long productID;



    public BatchStockResponseDTO(Long batchStockNumber, Long currentTemperature, Long minimumTemperature, String initialQuality, String currentQuality, LocalDateTime manufacturingTime, LocalDate dueDate, Long productID) {
        this.batchStockNumber = batchStockNumber;
        this.currentTemperature = currentTemperature;
        this.minimumTemperature = minimumTemperature;
        this.initialQuality = initialQuality;
        this.currentQuality = currentQuality;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
        this.productID = productID;
    }

    public BatchStockResponseDTO(BatchStock batchStock) {
        this.batchStockNumber = batchStock.getBatchStockNumber();
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.initialQuality = batchStock.getInitialQuality();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.dueDate = batchStock.getDueDate();
        this.productID = batchStock.getProductID();
    }

    public static BatchStockResponseDTO converte(BatchStock batchStock) {
        return new BatchStockResponseDTO(batchStock.getBatchStockNumber(),
                batchStock.getCurrentTemperature(),
                batchStock.getMinimumTemperature(),
                batchStock.getInitialQuality(),
                batchStock.getCurrentQuality(),
                batchStock.getManufacturingTime(),
                batchStock.getDueDate(),
                batchStock.getProductID());
    }

}
