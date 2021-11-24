package com.meli.projetointegradorgroup1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class BatchStockRequestDTO {

    @NotNull(message = "Campo batchStockNumber é obrigatorio")
    private Long batchStockNumber;

    @NotNull(message = "Campo batchStockItem é obrigatorio")
    private Long batchStockItem;

    private Long sellerId;

    @NotNull (message = "Campo currentTemperature é obrigatorio")
    private double currentTemperature;

    @NotNull(message = "Campo minimumTemperature é obrigatorio")
    private double minimumTemperature;

    @NotNull(message = "Campo maximumTemperature é obrigatorio")
    private double maximumTemperature;

    @NotBlank(message = "campo initialQuality é obrigatorio")
    private String initialQuality;

    @NotBlank(message = "campo currentQuality é obrigatorio")
    private String currentQuality;

    @NotBlank(message = "campo manufacturingTime é obrigatorio")
    private String  manufacturingTime;

    @NotNull(message = "campo dueDate é obrigatorio")
    private LocalDate dueDate;

    @NotNull(message = "campo dueDate é obrigatorio")
    private int quantity; //quantidade de produtos chegando no lote

    @NotNull(message = "campo dueDate é obrigatorio")
    private double volume; //volume total ocupado pelo lote de produtos

}
