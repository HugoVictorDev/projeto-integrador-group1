package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Hugo Victor
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockItemResponseDTO {

    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Long product_id;

    public BatchStockItemResponseDTO(BatchStockItem batchStockItem){
        this.quantity = batchStockItem.getQuantity();;
        this.volume = batchStockItem.getVolume();
        this.maximumTemperature = batchStockItem.getMaximumTemperature();
        this.minimumTemperature = batchStockItem.getMinimumTemperature();
        this.product_id = batchStockItem.getProduct().getId();

    }

}
