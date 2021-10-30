package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseDTOHugo {


    private Long warehouseId;

    public Warehouse build(){

        Warehouse warehouse = new Warehouse()
                .setWarehouseId(this.warehouseId);

        return warehouse;
    }




}
