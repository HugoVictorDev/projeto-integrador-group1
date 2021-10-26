package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class InBoundOrderDTO {

    private Long orderNumber;

    @NotBlank @NotNull @NotEmpty(message = "representative é obrigatorio")
    private Representative representative;

    @NotBlank @NotNull @NotEmpty(message = "warehouseID é obrigatorio")
    private BatchStock batchStock;

    @NotBlank @NotNull @NotEmpty(message = "campo orderDate é obrigatorio")
    private LocalDate orderDate;



    public InBoundOrderDTO() {
    }

    public InBoundOrderDTO(Long orderNumber, Representative representative, BatchStock batchStock, LocalDate orderDate) {
        this.orderNumber = orderNumber;
        this.representative = representative;
        this.batchStock = batchStock;
        this.orderDate = orderDate;
    }



    public Long getOrderNumber() {
        return orderNumber;
    }

    public Representative getRepresentative() {
        return representative;
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BatchStock getBatchStock() {
        return batchStock;
    }


    public InBoundOrder converte(InBoundOrderDTO dto){
        return new InBoundOrder(dto.getOrderNumber(), dto.getRepresentative(), dto.getBatchStock() , dto.getOrderDate()) ;
    }



    public static InBoundOrderDTO converte(InBoundOrder inboundOrder){
        return new InBoundOrderDTO(inboundOrder.getOrderNumber(),inboundOrder.getRepresentative(),inboundOrder.getBatchStock(),inboundOrder.getOrderDate());
    }


}
