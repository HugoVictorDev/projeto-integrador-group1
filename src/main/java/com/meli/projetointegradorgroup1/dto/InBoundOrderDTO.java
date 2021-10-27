package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InBoundOrderDTO {

    private Long orderNumber;

    @NotBlank(message = "representative é obrigatorio")
    private Representative representative;

    @NotBlank(message = "warehouseID é obrigatorio")
    private List<BatchStock> batchStock;

    @NotBlank(message = "campo orderDate é obrigatorio")
    private LocalDate orderDate;



//    public InBoundOrder converte(InBoundOrderDTO dto){
//        return new InBoundOrder(dto.getOrderNumber(), dto.getRepresentative(),
//                dto.getBatchStock() , dto.getOrderDate()) ;
//    }



    public static InBoundOrderDTO converte(InBoundOrder inboundOrder){
        return new InBoundOrderDTO(inboundOrder.getOrderNumber(),
                inboundOrder.getRepresentative(),
                inboundOrder.getBatchStock() ,inboundOrder.getOrderDate());
    }


}
