package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.dto.WarehouseDTOHugo;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTOHugo {

    private Long sectionId;
    private WarehouseDTOHugo warehouseDTOHugo;



    public Section build(){

        Section section = new Section()
                .setSectionId(this.sectionId)
                .setWarehouse(warehouseDTOHugo.build())
                ;

        return section;
    }


}
