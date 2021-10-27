package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class WarehouseDTO {

    private Long warehouseId;

    @NotBlank(message = "Campo é obrigatorio")
    @Pattern(regexp="^[a-zA-Z]+(?:\\s[a-zA-Z]+)?${3,50}",message="Apenas letras, minimo 3 caracteres")
    private String name;

    @NotBlank(message = "Campo é obrigatorio")
    private String address;

    @NotBlank(message = "campo é obrigatorio")
    @Pattern(regexp="^[0-9]",message = "Deve conter apenas numeros")
    private String size;


    public WarehouseDTO(Long warehouseId, String name, String address, String size) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
        this.size = size;

    }

    public static Warehouse converte(WarehouseDTO warehouseDTO) {
        return new Warehouse().Name(warehouseDTO.getName())
                              .Address(warehouseDTO.getAddress())
                              .Size(warehouseDTO.getSize());
    }

    public static WarehouseDTO converte(Warehouse wareHouse) {
        return new WarehouseDTO(wareHouse.getWarehouseId(),wareHouse.getName(),wareHouse.getAddress(),wareHouse.getSize());
    }

    public Iterable<WarehouseDTO> converte(List<Warehouse> warehouses) {
        List<WarehouseDTO> listWarehouse = new ArrayList<>();
        for (Warehouse warehouse: warehouses) {
            listWarehouse.add(new WarehouseDTO(warehouse.getWarehouseId(), warehouse.getName(), warehouse.getAddress(), warehouse.getSize()));
        }
        return listWarehouse;
    }
}
