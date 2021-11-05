
package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//conjunto de lote
public class BatchStockDTO {


    private Long batchStockNumber;

    private Long productID;

//    @NotNull(message = "currentTemprature é obrigatorio")
    private Long currentTemperature;

//    @NotNull(message = "minimumTemprature é obrigatorio")
    private Long minimumTemperature;

//    @NotBlank(message = "initialQuality é obrigatorio")
    private String initialQuality;

//    @NotNull(message = "minimumTemprature é obrigatorio")
    private Long batchStockItem;

//    @NotBlank(message = "currentQuality é obrigatorio")
    private String currentQuality;

    private String manufacturingTime;
    private String dueDate;



    public static BatchStock converte(BatchStockDTO batchStockdto) {
        return null;
        //TODO: Revisar
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return new BatchStock().CurrentTemperature(batchStockdto.getCurrentTemperature())
//                                .MinimumTemperature(batchStockdto.getMinimumTemperature())
//                                .InitialQuality(batchStockdto.getInitialQuality())
//                                .CurrentQuality(batchStockdto.getCurrentQuality())
//                                .ManufacturingTime(LocalDateTime.parse(batchStockdto.getManufacturingTime(),formatter))
//                                .DueDate(LocalDate.parse(batchStockdto.getDueDate()))
//                                .ProductID(batchStockdto.getBatchStockItem());
    }

}



