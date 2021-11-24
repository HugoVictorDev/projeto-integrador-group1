package com.meli.projetointegradorgroup1.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Hugo Victor
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionForInboundDTO {

    @JsonProperty(value = "sectionCode")
    private Long code;
    private Long warehouseCode;

}
