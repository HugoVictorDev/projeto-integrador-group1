package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.InboundOrderDtoJustBatchStocks;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
/**
 * @author Marco Siqueiraa
 */

public class InBoundOrderControllerTest {
    InBoundOrderController inBoundOrderController;
    InBoundOrderService inBoundOrderService;

    InboundOrderDtoJustBatchStocks  inboundOrderDtoJustBatchStocks = new InboundOrderDtoJustBatchStocks();
    InBoundOrderRequestDTO inBoundOrderRequestDTO = new InBoundOrderRequestDTO();
    InBoundOrder inBoundOrder = new InBoundOrder(1l,2l, LocalDate.now(), null, null, null);

    @Test
    public void create(){
        inBoundOrderController = Mockito.mock(InBoundOrderController.class);
        inBoundOrderService = Mockito.mock(InBoundOrderService.class);
        inBoundOrderRequestDTO = Mockito.mock(InBoundOrderRequestDTO.class);
        Mockito.when(inBoundOrderService.validInboundOrder(Mockito.any())).thenReturn(inBoundOrderRequestDTO);
        Mockito.when(inBoundOrderService.registra(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.accepted().body(inBoundOrder));
        Mockito.when(inBoundOrderRequestDTO.convertedto(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(inBoundOrder);
        InBoundOrderController inBoundOrderController = new InBoundOrderController(inBoundOrderService);
        inBoundOrderController.create(inBoundOrderRequestDTO,null);
        assert(inBoundOrder.getId() != null);
    }

    @Test
    public void update(){
        inBoundOrderService = Mockito.mock(InBoundOrderService.class);
        Mockito.when(inBoundOrderService.validInboundOrder(Mockito.any())).thenReturn(inBoundOrderRequestDTO);
        Mockito.when(inBoundOrderService.updateInbound(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.created(Mockito.any()).body(null));
        InBoundOrderController inBoundOrderController = new InBoundOrderController(inBoundOrderService);
        assert (inBoundOrderController.update(inBoundOrderRequestDTO,null).getStatusCodeValue() == 201 );
    }

    @Test
    public void listBatchStock(){
        inBoundOrderService = Mockito.mock(InBoundOrderService.class);
        Mockito.when(inBoundOrderService.converteDto(Mockito.any())).thenReturn(inboundOrderDtoJustBatchStocks);
        Mockito.when(inBoundOrderService.listInboundRepresentante(Mockito.anyLong())).thenReturn(inBoundOrder);
        InBoundOrderController inBoundOrderController = new InBoundOrderController(inBoundOrderService);
        assert (inBoundOrderController.listBatchsStocksFromInbound(1l)!=null);

    }
}
