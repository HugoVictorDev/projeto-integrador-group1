package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.entity.StockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Marco Siqueira
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SectionResponseDTO {

    private Long code;
    private String minimumTemperature;
    private StockType stockType;
    private Long capacity;
    private Long warehouseID;
}