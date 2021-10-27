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

    @NotBlank
    @Size(min = 3, message = "minimo 3 letras")
    private int quantity;
    private Double volume;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private BatchStock batchstock;
    private Product product;
    private Seller seller;


     public BatchStockItem build(){
         BatchStockItem batchStockItem = new BatchStockItem()
                 .setQuantity(this.quantity)
                 .setMaximumTemperature(this.maximumTemperature)
                 .setMinimumTemperature(this.minimumTemperature)
                 .setBatchstock(this.batchstock)
                 .setProduct(this.product)
                 .setSeller(this.seller)
                 ;
        return batchStockItem;
    }

}
