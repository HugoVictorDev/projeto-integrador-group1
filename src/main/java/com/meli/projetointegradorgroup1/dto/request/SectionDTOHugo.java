package com.meli.projetointegradorgroup1.dto.request;


import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTOHugo {

    private Long sectionCode;
    private Long warehouseCode;


    public Section convertedto(){
        Warehouse warehouse = new Warehouse().setId(warehouseCode);
        Section section = new Section()
                .setId(this.sectionCode)
                .setWarehouse(warehouse);

        return section;
    }


}
