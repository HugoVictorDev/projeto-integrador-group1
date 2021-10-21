package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RepresentativeDTO {

    private Long representative_Id;
    @NotBlank @NotNull @NotEmpty(message = "campo Name é obrigatorio")
    private String name;
    @NotBlank @NotNull @NotEmpty(message = "campo CPF é obrigatorio")
    private String cpf;
    @NotNull(message = "warehouseID é obrigatorio")
    private Long warehouseID;

    public RepresentativeDTO() {
    }

    public RepresentativeDTO(Long representative_Id, String name, String cpf, Long warehouseID) {
        this.representative_Id = representative_Id;
        this.name = name;
        this.cpf = cpf;
        this.warehouseID = warehouseID;
    }

    public static Representative converte(RepresentativeDTO dto){
        return new  Representative().Name(dto.getName())
                                    .Cpf(dto.getCpf())
                                    .WarehouseID(dto.getWarehouseID());
    }

    public static RepresentativeDTO converte(Representative representative){
        return new RepresentativeDTO(representative.getRepresentative_Id(), representative.getName(), representative.getCpf(), representative.getWarehouse().getWarehouseId());
    }
}