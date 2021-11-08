package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTO {

    private Long code;

    //  @NotNull(message = "Campo é obrigatorio")
    @Pattern(regexp="^[-+]?([0-9][0-9]?|100)$",message = "tempereratura minima inválida")
    private String minimumTemperature;

    @NotBlank(message = "Campo é obrigatorio")
    private String stock;

    @NotBlank(message = "Campo é obrigatorio")
    private String stockType;



    private Long warehouseID;

public Section converteBuilder(SectionDTO dto, WarehouseServices warehouseServices){
    return Section.builder()
            .code(dto.code)
            .minimumTemperature(dto.getMinimumTemperature())
            .stock(dto.getStock())
            .stockType(dto.getStockType())
            .warehouse(warehouseServices.obterWarehouse(dto.getWarehouseID()))
            .build();
}

    public static Section converte(SectionDTO sectiodto, WarehouseServices warehouseServices) {
        return new Section().setMinimumTemperature(sectiodto.getMinimumTemperature())
                .setStock(sectiodto.getStock())
                .setStockType(sectiodto.getStockType())
                .setWarehouse(warehouseServices.obterWarehouse(sectiodto.getWarehouseID()));
    }

    public static SectionDTO converte(Section section) {
        return new SectionDTO(section.getCode(), section.getMinimumTemperature(), section.getStock(), section.getStockType()
                ,section.getWarehouse().getId());
    }

    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add(new SectionDTO( section.getCode(), section.getMinimumTemperature(), section.getStock(), section.getStockType(),
                   section.getWarehouse().getId()));
        }
        return listaSection;
    }
}