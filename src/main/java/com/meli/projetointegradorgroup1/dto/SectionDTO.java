package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
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
    private String warehouseID;

    public SectionDTO() {
    }

    public SectionDTO(Long sectionId, String minimumTemprature, String stock, String stockType, String warehouseID) {
        this.sectionId = sectionId;
        this.minimumTemperature = minimumTemprature;
        this.stock = stock;
        this.stockType = stockType;

        this.warehouseID = warehouseID;
    }


    public static Section converte(SectionDTO sectiodto) {
        return new Section().MinimumTemprature(sectiodto.getMinimumTemperature())
                            .Stock(sectiodto.getStock())
                            .StockType(sectiodto.getStockType())

                            .WarehouseID(Long.parseLong(sectiodto.getWarehouseID()));
    }

    public static SectionDTO converte(Section section) {
        return new SectionDTO(section.getSectionId(), section.getMinimumTemperature(), section.getStock(), section.getStockType()
                , Long.toString(section.getWarehouse().getWarehouseId()));
    }

    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add(new SectionDTO(section.getSectionId(), section.getMinimumTemperature(), section.getStock(), section.getStockType(),
                    Long.toString(section.getWarehouse().getWarehouseId())));
        }
        return listaSection;
    }
}
