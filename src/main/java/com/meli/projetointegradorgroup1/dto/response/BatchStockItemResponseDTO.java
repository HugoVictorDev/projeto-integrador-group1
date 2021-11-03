package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockItemResponseDTO {

    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Long seller_id;
    private Long product_id;

    public BatchStockItemResponseDTO(BatchStockItem batchStockItem){
        this.quantity = batchStockItem.getQuantity();;
        this.volume = batchStockItem.getVolume();
        this.maximumTemperature = batchStockItem.getMaximumTemperature();
        this.minimumTemperature = batchStockItem.getMinimumTemperature();
//        this.seller_id = batchStockItem.getSellerIdConvert();
//        this.product_id = batchStockItem.getProductIdConvert();
        //TODO: revisar
    }

}
