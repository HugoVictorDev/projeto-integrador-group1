package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;

import com.meli.projetointegradorgroup1.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

//Essa classe nao conecta com BD, intermedio entre o usuario e a classe
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockItemRequestDTO {


    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;

    private Product product;
    private String seller_id;


     public BatchStockItem build(){
         BatchStockItem batchStockItem = new BatchStockItem()
                 .setQuantity(this.quantity)
                 .setMaximumTemperature(this.maximumTemperature)
                 .setMinimumTemperature(this.minimumTemperature)
                 .setProduct(this.product)
                 .setId(Long.parseLong(seller_id))
                 ;
        return batchStockItem;
    }

}
