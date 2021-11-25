package com.meli.projetointegradorgroup1.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
/**
 * @author Marco Siqueira
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BatchStockResponseDTO {

    private Long  batchStockNumber;
    private Long sellerId;
    private double currentTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
    private String initialQuality;
    private String currentQuality;
    private String manufacturingTime;
    private LocalDate dueDate;
    private int quantity;
    private double volume;

}
