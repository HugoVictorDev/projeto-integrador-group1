package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.Data;


@Data
public class WarehouseDTOHugo {


    private Long warehouseId;

    public Warehouse build(){

        Warehouse warehouse = new Warehouse()
                .setWarehouseId(this.warehouseId);

        return warehouse;
    }




}
