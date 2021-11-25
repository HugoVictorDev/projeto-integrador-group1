package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTOList {

    private List<BatchStockRequestDTO> batchStockDTOList;


}
