package com.meli.projetointegradorgroup1.services;


import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InBoundOrderServiceTest {

    String stockType = "FRESH";
    SectionForInboundDTO sectionForInboundDTO = new SectionForInboundDTO(1l, 1l);
    BatchStockItem batchStockItem = new BatchStockItem(1l, 2, 3.0, 4.0, 5.0,null, null );
    Seller seller = new Seller (1l, "36843012809", "Edenilson", "edenilson.paschoal@mercadolivre.com");
    BatchStockRequestDTO batchStockRequestDTO = new BatchStockRequestDTO(1l,2l,1l,2.0,3.0,4.0,"5","6", "2021-11-16 00:00:00",LocalDate.now(), 7, 8.0);
    List<BatchStockRequestDTO> listBatchStockDto = new ArrayList();
    List<Section> listSection = new ArrayList();

    Representante representante = new Representante(1l, "Joao", "98765432178");
    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",representante);
    Section section = new Section(1l, 2l, StockType.FRESH,"30", 8l, warehouse);
    BatchStock batchStock = new BatchStock(1l, 2l,2.0,3.0,4.0,"5","6", LocalDateTime.now(), LocalDate.now(), 7, 8.0, batchStockItem,seller,null);

    SectionServices sectionServices;
    List<BatchStock> batchStockList = new ArrayList();
    RepresentanteServices representanteServices;
    WarehouseServices warehouseServices;
    ProductService productService;
    InBoundOrderRepository inBoundOrderRepository;
    InBoundOrderService inBoundOrderService;
    InBoundOrderRequestDTO inBoundOrderRequestDTO = new InBoundOrderRequestDTO(1l,LocalDate.now(), 3l,  sectionForInboundDTO, listBatchStockDto, 1l);

    InBoundOrder inBoundOrder = new InBoundOrder(1l,2l, LocalDate.now(), null, batchStockList, null);

    String message = null;
    @Test
    public void registra(){
        inBoundOrderRepository = Mockito.mock(InBoundOrderRepository.class);
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenReturn(null);

        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,null,null, null);
        inBoundOrderService.registra(inBoundOrder);

        assert(inBoundOrder.getId() != null);
    }

    @Test
    public void registraNok(){
        inBoundOrderRepository = Mockito.mock(InBoundOrderRepository.class);
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,null,null, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        inBoundOrderService.registra(inBoundOrder);});
        message = "Erro ao Registrar InboundOrder";

        assert (exception.getMessage().contains(message));
    }

    @Test
    public void validInboundOrder(){
        listSection.add(section);

        warehouseServices = Mockito.mock(WarehouseServices.class);
        representanteServices = Mockito.mock(RepresentanteServices.class);
        sectionServices = Mockito.mock(SectionServices.class);
        inBoundOrderService = Mockito.mock(InBoundOrderService.class);

        Mockito.when(warehouseServices.obterWarhouseByCode(Mockito.anyLong())).thenReturn(null);
        Mockito.when(sectionServices.obterSectionByCode(Mockito.anyLong())).thenReturn(null);
        Mockito.when(representanteServices.obterRepresentanteById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(sectionServices.obtemTypeStockSection(Mockito.anyLong())).thenReturn(StockType.valueOf(stockType));
        Mockito.when(sectionServices.listaSection()).thenReturn(listSection);

        InBoundOrderService inBoundOrderService= new InBoundOrderService(null,warehouseServices,representanteServices,null, sectionServices);

        assert(inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO) != null);
    }

    @Test
    public void converte(){

    }




}
