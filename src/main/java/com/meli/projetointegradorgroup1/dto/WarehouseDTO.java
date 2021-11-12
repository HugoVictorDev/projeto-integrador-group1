package com.meli.projetointegradorgroup1.dto;


import lombok.Data;
import javax.validation.constraints.*;


@Data
public class WarehouseDTO {

    private Long warehouseId;

    @NotBlank(message = "Campo é obrigatorio")
    private Long code;

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
}
