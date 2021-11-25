package com.meli.projetointegradorgroup1.dto.response;
import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/**
 * @author Hugo Victor
 * Requisito 6 Individual
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDtoJustBatchStocks {

private List<BatchStockRequestDTO> batchStockDTOList;


}
