package com.meli.projetointegradorgroup1.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Hugo Victor
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockItemRequestDTO {

    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Long product_id;

}
