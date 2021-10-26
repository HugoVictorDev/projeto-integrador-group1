package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;

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
    private List<Product> productlist;
    private BatchStock batchstock;


     public BatchStockItem build(){
         BatchStockItem batchStockItem = new BatchStockItem()
                 .setBatchstock(this.batchstock)
                 .setQuantity(this.quantity)
                 .setProductlist(this.productlist);
        return batchStockItem;
    }

}
