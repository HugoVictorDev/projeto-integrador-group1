package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.services.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SectionDTO {

    private Long code;
    private String minimumTemperature;
    private StockType stockType;
    private Long capacity;
    private Long warehouseID;
}