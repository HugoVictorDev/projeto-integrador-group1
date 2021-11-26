package com.meli.projetointegradorgroup1.services;


import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Marco Siqueiraa
 */

public class InBoundOrderServiceTest {

    String stockType = "FRESH";
    Product product = new Product(1l, "teste","cafe", StockType.FRESH);
    SectionForInboundDTO sectionForInboundDTO = new SectionForInboundDTO(1l, 1l);
    BatchStockItem batchStockItem = new BatchStockItem(1l, 2, 3.0, 4.0, 5.0,null, null );
    Seller seller = new Seller (1l, "36843012809", "Edenilson", "edenilson.paschoal@mercadolivre.com");
    BatchStockRequestDTO batchStockRequestDTO = new BatchStockRequestDTO(1l,2l,1l,2.0,3.0,4.0,"5","6", "2021-11-16 00:00:00",LocalDate.now(), 7, 8.0);
    List<BatchStockRequestDTO> listBatchStockDto = new ArrayList();
    List<Section> listSection = new ArrayList();

    Representante representante = new Representante(1l, "Joao", "98765432178");
    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",representante);
    Section section = new Section(1l, 2l, StockType.FRESH,"30", 8l, warehouse);
    BatchStock batchStock = new BatchStock(1l, 2l,2.0,3.0,4.0,"5","6", LocalDateTime.now(), LocalDate.now(). plusDays(10), 7, 8.0, batchStockItem,seller);

    SectionServices sectionServices = Mockito.mock(SectionServices.class);
    List<BatchStock> batchStockList = new ArrayList();
    RepresentanteServices representanteServices = Mockito.mock(RepresentanteServices.class);
    WarehouseServices warehouseServices = Mockito.mock(WarehouseServices.class);
    ProductService productService = Mockito.mock(ProductService.class);
    InBoundOrderRepository inBoundOrderRepository = Mockito.mock(InBoundOrderRepository.class);
    SellerService sellerService = Mockito.mock(SellerService.class);
    InBoundOrderRequestDTO inBoundOrderRequestDTO = new InBoundOrderRequestDTO(1l,LocalDate.now(), 3l,  sectionForInboundDTO, listBatchStockDto, 1l);
    BatchStockService batchStockService = Mockito.mock(BatchStockService.class);

    InBoundOrder inBoundOrder = new InBoundOrder(1l,2l, LocalDate.now(), null, batchStockList, null);

    String uri = "http//Mock";

    @Test
    public void registraOK(){
        batchStockList.add(batchStock);
        batchStockList.add(batchStock);
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenReturn(null);
        Mockito.doNothing().when(batchStockService).validaDate(Mockito.eq(LocalDate.now()),Mockito.eq(LocalDateTime.now()),Mockito.eq(LocalDate.now()),Mockito.anyLong());
        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,null, productService,
                null, null, null, batchStockService);
        assert (inBoundOrderService.registra(uriBuilder,inBoundOrderRequestDTO,inBoundOrder).getStatusCodeValue() == 201 );
    }

    @Test
    public void registraNok(){
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,null, productService,
                null, null, null, null);
        assert (inBoundOrderService.registra(null,null,inBoundOrder).getStatusCodeValue() == 400 );
    }

    @Test
    public void validInboundOrderOK(){
        listSection.add(section);
        Mockito.when(warehouseServices.obterWarhouseByCode(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.when(sectionServices.obterSectionByCode(Mockito.anyLong())).thenReturn(null);
        Mockito.when(representanteServices.obterRepresentanteById(Mockito.anyLong())).thenReturn(representante);
        Mockito.when(sectionServices.obtemTypeStockSection(Mockito.anyLong())).thenReturn(StockType.valueOf(stockType));
        Mockito.when(sectionServices.listaSection()).thenReturn(listSection);
        Mockito.when(sellerService.findSellerById(Mockito.anyLong())).thenReturn(seller);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(null,warehouseServices,representanteServices, productService,
                sellerService,  sectionServices, null, null);
        assert(inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO) != null);
    }
    @Test
    public void updateInboundAddOk(){
        listBatchStockDto.add(batchStockRequestDTO);
        batchStockList.add(batchStock);
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(inBoundOrderRepository.findByOrderNumber(Mockito.anyLong())).thenReturn(inBoundOrder);
        Mockito.when(sectionServices.obterSectionByCode(Mockito.anyLong())).thenReturn(section);
        Mockito.when(inBoundOrderRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(inBoundOrder));
        Mockito.when(representanteServices.obterRepresentanteById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenReturn(null);
        Mockito.when(batchStockService.findBatchNumber(Mockito.anyLong())).thenReturn(batchStock);
        Mockito.when(sellerService.findSellerById(Mockito.anyLong())).thenReturn(seller);
        Mockito.when(productService.obtem(Mockito.anyLong())).thenReturn(product);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,representanteServices, productService,
                sellerService,  sectionServices, null, batchStockService);
        assert (inBoundOrderService.updateInbound(inBoundOrderRequestDTO,uriBuilder).getStatusCodeValue()==201);
    }

    @Test
    public void updateInboundUpOk(){
        listBatchStockDto.add(batchStockRequestDTO);
        batchStockList.add(batchStock);
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(inBoundOrderRepository.findByOrderNumber(Mockito.anyLong())).thenReturn(inBoundOrder);
        Mockito.when(sectionServices.obterSectionByCode(Mockito.anyLong())).thenReturn(section);
        Mockito.when(inBoundOrderRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(inBoundOrder));
        Mockito.when(representanteServices.obterRepresentanteById(Mockito.anyLong())).thenReturn(null);
        Mockito.when(inBoundOrderRepository.save(Mockito.any())).thenReturn(null);
        Mockito.when(batchStockService.findBatchNumber(Mockito.anyLong())).thenReturn(null);
        Mockito.when(sellerService.findSellerById(Mockito.anyLong())).thenReturn(seller);
        Mockito.when(productService.obtem(Mockito.anyLong())).thenReturn(product);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,representanteServices, productService,
                sellerService,  sectionServices, null, batchStockService);
        assert (inBoundOrderService.updateInbound(inBoundOrderRequestDTO,uriBuilder).getStatusCodeValue()==201);
    }

    @Test
    public void converteDto(){
        batchStockList.add(batchStock);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(null,null,null, productService,
                sellerService,  null, null, null);
        assert (inBoundOrderService.converteDto(inBoundOrder)!=null);
    }


    @Test
    public void listInboundRepresentante(){
        Mockito.when(representanteServices.obterRepresentanteById(Mockito.anyLong())).thenReturn(representante);
        Mockito.when(inBoundOrderRepository.findByRepresentante_Id(Mockito.anyLong())).thenReturn(inBoundOrder);
        InBoundOrderService inBoundOrderService= new InBoundOrderService(inBoundOrderRepository,null,representanteServices, null,
                null,  null, null, null);
        assert (inBoundOrderService.listInboundRepresentante(1l)!=null);
    }

}
