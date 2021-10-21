package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.Data;

@Data
public class RepresentativeDTO {

    private String name;
    private String cpf;
    private Long warehouseID;

    public RepresentativeDTO(String name, String cpf, Long warehouseID) {
        this.name = name;
        this.cpf = cpf;
        this.warehouseID = warehouseID;
    }


    public RepresentativeDTO() {
    }



    public static Representative converte(RepresentativeDTO dto){
        return new Representative().Name(dto.getName())
                .Cpf(dto.getCpf())
                .WarehouseID(dto.getWarehouseID());


    }
}