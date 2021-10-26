package com.meli.projetointegradorgroup1.dto.response;

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
public class BatchstockItemResponseDTO {

    private int quantity;
    private List<Product> productlist;
    private BatchStock batchstock;

    public BatchstockItemResponseDTO(BatchStockItem batchStockItem){
        this.quantity = batchStockItem.getQuantity();
        this.productlist = batchStockItem.getProductlist();
        this.batchstock = batchStockItem.getBatchstock();

    }






}
