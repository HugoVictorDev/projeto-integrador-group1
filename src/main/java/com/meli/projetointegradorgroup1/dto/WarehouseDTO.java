package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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


    public static Warehouse converte(WarehouseDTO dto) {
        return Warehouse.builder().name(dto.getName()).address(dto.getAddress()).size(dto.getSize()).build();
    }

    public static WarehouseDTO converte(Warehouse wareHouse) {
        return WarehouseDTO.builder().name(wareHouse.getName()).address(wareHouse.getAddress()).size(wareHouse.getSize()).warehouseId(wareHouse.getId()).build();
    }

    public Iterable<WarehouseDTO> converte(List<Warehouse> warehouses) {
        List<WarehouseDTO> listWarehouse = new ArrayList<>();
        for (Warehouse w: warehouses) {
            listWarehouse.add(
                    WarehouseDTO.builder().name(w.getName())
                            .address(w.getAddress())
                            .warehouseId(w.getId())
                            .build()

            );
        }
        return listWarehouse;
    }
}
