package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    public static Representative converte(RepresentativeDTO dto){
        return Representative.builder()
                .name(dto.getName())
                .cpf(RepresentativeServices.maskCpf(dto.getCpf())).build();

    }

    public static RepresentativeDTO converte(Representative Representative) {
        return RepresentativeDTO.builder()
                .cpf(Representative.getCpf())
                .name(Representative.getName())
                .representative_Id(Representative.getId())
                .build();
    }

    public static List<RepresentativeDTO>converte(List<Representative> representatives){
        List<RepresentativeDTO> listRepresentative = new ArrayList<>();
        for (Representative representative: representatives) {
            listRepresentative.add(
                    RepresentativeDTO.builder()
                            .representative_Id(representative.getId())
                            .name(representative.getName())
                            .cpf(representative.getCpf())
                            .build()
            );
        }
        return listRepresentative;
    }
}