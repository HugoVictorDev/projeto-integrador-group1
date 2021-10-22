package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import javax.validation.constraints.*;
import java.text.ParseException;

@Service
@Data
public class RepresentativeDTO {

    private Long representative_Id;

    @NotBlank @NotNull @NotEmpty(message = "Campo Name é obrigatorio")
    @Pattern(regexp="^[a-zA-Z]+(?:\\s[a-zA-Z]+)?${3,50}",message="Apenas letras, minimo 3 caracteres")
    private String name;

    @NotBlank @NotNull @NotEmpty(message = "Campo CPF é obrigatorio")
    @Pattern(regexp="^[0-9]{11}",message = "Deve conter exatamente 11 digitos e apenas numeros, ")
    private String cpf;

    @NotBlank @NotNull @NotEmpty(message = "warehouseID é obrigatorio")
    @Pattern(regexp="^[0-9]",message = "Deve conter apenas numeros,")
    private String warehouseID;

    public RepresentativeDTO() {
    }

    public RepresentativeDTO(Long representative_Id, String name, String cpf, String warehouseID) {
        this.representative_Id = representative_Id;
        this.name = name;
        this.cpf = cpf;
        this.warehouseID = warehouseID;
    }

    public static Representative converte(RepresentativeDTO dto){
        return new  Representative().Name(dto.getName())
                                    .Cpf(RepresentativeServices.maskCpf(dto.getCpf()))
                                    .WarehouseID(Long.parseLong(dto.getWarehouseID()));
    }

    public static RepresentativeDTO converte(Representative representative){
        return new RepresentativeDTO(representative.getRepresentative_Id(), representative.getName(), representative.getCpf(), Long.toString(representative.getWarehouse().getWarehouseId()));
    }

}