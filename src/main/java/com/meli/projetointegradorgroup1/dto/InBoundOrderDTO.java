package com.meli.projetointegradorgroup1.dto;

import com.meli.projetointegradorgroup1.entity.InboundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InBoundOrderDTO {

    private Long orderNumber;

    @NotBlank @NotNull @NotEmpty(message = "representative é obrigatorio")
    private Representative representative;

    @NotBlank @NotNull @NotEmpty(message = "campo orderDate é obrigatorio")
    private String orderDate;

    @NotBlank @NotNull @NotEmpty(message = "warehouseID é obrigatorio")
    private Long warehouseID;

    public InBoundOrderDTO() {
    }

    public InBoundOrderDTO(Long orderNumber, Representative representative, String orderDate) {
        this.orderNumber = orderNumber;
        this.representative = representative;
        this.orderDate = orderDate;
    }

    public static InboundOrder converte(InBoundOrderDTO dto){
        return new  InboundOrder(dto.getOrderNumber(), dto.getRepresentative(), dto.getOrderDate());
    }

    public static RepresentativeDTO converte(Representative representative){
        return new RepresentativeDTO(representative.getRepresentative_Id(), representative.getName(), representative.getCpf(), representative.getWarehouse().getWarehouseId());
    }
}
