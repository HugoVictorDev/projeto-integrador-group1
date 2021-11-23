package com.meli.projetointegradorgroup1.dto.response;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InBoundOrderResponseDTO {

    private Long orderNumber;
    private LocalDate orderDate;
    private Long sellerId;
    private SectionForInboundDTO sectionForInboundDTO;
    private List<BatchStockRequestDTO> batchStockDTOList;
    private Long representanteId;
}
