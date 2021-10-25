package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RepresentativeResponseDTO {

    private Long representative_Id;
    private String name;
    private String cpf;

    @Autowired
    private Warehouse warehouse;

    public RepresentativeResponseDTO() {
    }

    public RepresentativeResponseDTO(Long representative_Id, String name, String cpf, Warehouse warehouse ) {
        this.representative_Id = representative_Id;
        this.name = name;
        this.cpf = cpf;
        this.warehouse = warehouse;
    }

    public static List<RepresentativeResponseDTO>converte(List<Representative> representatives){
        List<RepresentativeResponseDTO> listRepresentant = new ArrayList<>();
        for (Representative representative: representatives) {
            listRepresentant.add(new RepresentativeResponseDTO(representative.getRepresentative_Id(), representative.getName(), representative.getCpf(), representative.getWarehouse()));
        }
        return listRepresentant;
    }

}