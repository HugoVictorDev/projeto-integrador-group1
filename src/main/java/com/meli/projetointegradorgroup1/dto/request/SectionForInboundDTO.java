package com.meli.projetointegradorgroup1.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.services.SectionServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionForInboundDTO {

    private Long sectionId;
    private Long warehouseId;

    public Section converte(SectionForInboundDTO dto, SectionServices sectionServices, WarehouseServices warehouseServices){
        return Section.builder()
                .id(dto.getSectionId())
                .warehouse(warehouseServices.obterWarehouse(dto.getWarehouseId()))
                .build();
    }


}
