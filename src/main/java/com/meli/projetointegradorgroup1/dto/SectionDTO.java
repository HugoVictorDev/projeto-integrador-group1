package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    private Long sectionId;

  //  @NotNull(message = "Campo é obrigatorio")
    @Pattern(regexp="^[-+]?([0-9][0-9]?|100)$",message = "tempereratura minima inválida")
    private String minimumTemperature;

    @NotBlank(message = "Campo é obrigatorio")
    private String stock;
    @NotBlank(message = "Campo é obrigatorio")

    private String stockType;
    @NotBlank(message = "Campo é obrigatorio")



    @NotBlank(message = "campo é obrigatorio")
    @Pattern(regexp="^[0-9]+$",message = "Deve conter apenas numeros")
    private Long warehouseID;


    public static SectionDTO converte(Section section) {
        return new SectionDTO(section.getSectionId(), section.getMinimumTemperature(), section.getStock(), section.getStockType()
                , section.getWarehouse().getWarehouseId());
    }

    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add(new SectionDTO(section.getSectionId(), section.getMinimumTemperature(), section.getStock(), section.getStockType(),
                    section.getWarehouse().getWarehouseId()));
        }
        return listaSection;
    }
}
