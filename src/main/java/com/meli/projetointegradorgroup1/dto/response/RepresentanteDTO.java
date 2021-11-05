package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
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
public class RepresentanteDTO {

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


    public static Representante converte(RepresentanteDTO dto){
        return Representante.builder()
                .name(dto.getName())
                .cpf(RepresentanteServices.maskCpf(dto.getCpf())).build();

    }

    public static RepresentanteDTO converte(Representante representante) {
        return RepresentanteDTO.builder()
                .cpf(representante.getCpf())
                .name(representante.getName())
                .representative_Id(representante.getId())
                .build();
    }

    public static List<RepresentanteDTO>converte(List<Representante> representatives){
        List<RepresentanteDTO> listRepresentant = new ArrayList<>();
        for (Representante representative: representatives) {
            listRepresentant.add(
                    RepresentanteDTO.builder()
                            .representative_Id(representative.getId())
                            .name(representative.getName())
                            .cpf(representative.getCpf())
                            .build()
            );
        }
        return listRepresentant;
    }
}