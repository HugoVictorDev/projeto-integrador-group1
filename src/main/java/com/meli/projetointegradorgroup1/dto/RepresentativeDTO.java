package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import javax.validation.constraints.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RepresentativeDTO {

    private Long representative_Id;

    @NotBlank(message = "Campo Name é obrigatorio")
    @Pattern(regexp="^[a-zA-Z]+(?:\\s[a-zA-Z]+)?${3,50}",message="Apenas letras, minimo 3 caracteres")
    private String name;

    @NotBlank(message = "Campo CPF é obrigatorio")
    @Pattern(regexp="^[0-9]{11}",message = "Deve conter exatamente 11 digitos e apenas numeros, ")
    private String cpf;

    @NotBlank(message = "campo é obrigatorio")
    @Pattern(regexp="^[0-9]+$",message = "Deve conter apenas numeros,")
    private String warehouseID;

    public RepresentativeDTO() {
    }

    public RepresentativeDTO(Long representative_Id, String name, String cpf, String warehouseID) {
        this.representative_Id = representative_Id;
        this.name = name;
        this.cpf = cpf;
        this.warehouseID = warehouseID;
    }

}