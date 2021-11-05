package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTO {

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
        return new SectionDTO( section.getMinimumTemperature(), section.getStock(), section.getStockType()
                ,section.getWarehouse().getId());
    }

    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add(new SectionDTO( section.getMinimumTemperature(), section.getStock(), section.getStockType(),
                   section.getWarehouse().getId()));
        }
        return listaSection;
    }
}