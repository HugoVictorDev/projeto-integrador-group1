package com.meli.projetointegradorgroup1.dto.request;

import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SectionRequestDTO {

    private Long sectionId;

    @NotNull(message = "Campo Capacity é obrigatorio")
    private Long code;

    @NotBlank(message = "Campo minimumTemperature é obrigatorio")
    @Pattern(regexp="^[-+]?([0-9][0-9]?|100)$",message = "tempereratura minima inválida")
    private String minimumTemperature;

    @NotNull(message = "Campo StockType é obrigatorio")
    private StockType stockType;

    @NotNull(message = "Campo Capacity é obrigatorio")
    private Long capacity;

    @NotNull(message = "Campo Warehouse é obrigatorio")
    private Long warehouseID;


}
