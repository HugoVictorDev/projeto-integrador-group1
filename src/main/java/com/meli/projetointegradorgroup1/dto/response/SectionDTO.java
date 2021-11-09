package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SectionDTO {

    private Long code;

    //  @NotNull(message = "Campo é obrigatorio")
    @Pattern(regexp="^[-+]?([0-9][0-9]?|100)$",message = "tempereratura minima inválida")
    private String minimumTemperature;
    private StockType stockType;
    private Long capacity;
    private Long warehouseID;

public Section converteBuilder(SectionDTO dto, WarehouseServices warehouseServices){
    return Section.builder()
            .code(dto.code)
            .stockType(stockType)
            .minimumTemperature(dto.getMinimumTemperature())
            .capacity(dto.capacity)
            .warehouse(warehouseServices.obterWarehouse(dto.getWarehouseID()))
            .build();
}


        public static SectionDTO converte(Section section){
            return SectionDTO.builder()
                    .code(section.getCode())
                    .stockType(section.getStockType())
                    .minimumTemperature(section.getMinimumTemperature())
                    .capacity(section.getCapacity())
                    .warehouseID(section.getWarehouse().getId())
                    .build();
        }


    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add( SectionDTO.builder()
                    .code(section.getCode())
                    .stockType(section.getStockType())
                    .minimumTemperature(section.getMinimumTemperature())
                    .capacity(section.getCapacity())
                    .warehouseID(section.getWarehouse().getId())
                    .build());
        }
        return listaSection;
    }
}