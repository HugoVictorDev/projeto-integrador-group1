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

    @NotBlank @NotNull @NotEmpty(message = "Campo é obrigatorio")
    @Pattern(regexp="^[0-9]",message = "Deve conter apenas numeros,")
    private String minimumTemprature;

    @NotBlank @NotNull @NotEmpty(message = "Campo é obrigatorio")
    @Min(value = 5, message = "campo deve te no minimo 5 caracteres")
    private String stock;
    @NotBlank @NotNull @NotEmpty(message = "Campo é obrigatorio")
    @Min(value = 5, message = "campo deve te no minimo 5 caracteres")
    private String stockType;
    @NotBlank @NotNull @NotEmpty(message = "Campo é obrigatorio")
    @Min(value = 5, message = "campo deve te no minimo 5 caracteres")
    private String batchStock;

    @NotBlank @NotNull @NotEmpty(message = "campo é obrigatorio")
    @Pattern(regexp="^[0-9]",message = "Deve conter apenas numeros,")
    private String warehouseID;

    public SectionDTO() {
    }

    public SectionDTO(Long sectionId, String minimumTemprature, String stock, String stockType, String batchStock, String warehouseID) {
        this.sectionId = sectionId;
        this.minimumTemprature = minimumTemprature;
        this.stock = stock;
        this.stockType = stockType;
        this.batchStock = batchStock;
        this.warehouseID = warehouseID;
    }

    public static Section converte(SectionDTO sectiodto) {
        return new Section().MinimumTemprature(sectiodto.getMinimumTemprature())
                            .Stock(sectiodto.getStock())
                            .StockType(sectiodto.getStockType())
                            .BatchStock(sectiodto.getBatchStock())
                            .WarehouseID(Long.parseLong(sectiodto.getWarehouseID()));
    }

    public static SectionDTO converte(Section section) {
        return new SectionDTO(section.getSectionId(), section.getMinimumTemprature(), section.getStock(), section.getStockType() ,section.getBatchStock(), Long.toString(section.getWarehouse().getWarehouseId()));
    }

    public Iterable<SectionDTO> converte(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add(new SectionDTO(section.getSectionId(), section.getMinimumTemprature(), section.getStock(), section.getStockType(), section.getBatchStock(), Long.toString(section.getWarehouse().getWarehouseId())));
        }
        return listaSection;
    }
}
