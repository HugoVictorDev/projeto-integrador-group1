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
    private Long seller_id;
    private Long product_id;


    public BatchStockItem build(){
//        Seller seller = new Seller()
//                .setSellerId(Long.parseLong(seller_id));
        BatchStockItem batchStockItem = new BatchStockItem()
                .setQuantity(this.quantity)
                .setVolume(this.volume)
                .setMaximumTemperature(this.maximumTemperature)
                .setMinimumTemperature(this.minimumTemperature)


                ;
        return batchStockItem;
    }

}
