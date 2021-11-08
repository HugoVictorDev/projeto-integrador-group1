package com.meli.projetointegradorgroup1.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.SectionServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionForInboundDTO {

    @JsonProperty(value = "sectionCode")
    private Long code;

    private Warehouse warehouseCode;
//    private Long warehouseCode;

    public Section converte(SectionForInboundDTO dto, WarehouseServices warehouseServices){
        Warehouse warehouse1 = warehouseServices.obterWarhouseByCode(warehouseCode.getCode());
        return Section.builder()
                .code(dto.code)
                .warehouse(warehouse1)
                .build();
    }


}
