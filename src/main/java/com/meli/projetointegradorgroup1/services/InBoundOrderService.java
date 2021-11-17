package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InBoundOrderService {

    @Autowired
    private InBoundOrderRepository inBoundOrderRepository;

    @Autowired
    private final WarehouseServices warehouseServices;

    @Autowired
    private  SectionServices sectionServices;

    @Autowired
    RepresentanteServices representanteServices;

    @Autowired
    ProductService productService;

    InBoundOrderRequestDTO inb;
    @Autowired
    public InBoundOrderService(InBoundOrderRepository inBoundOrderRepository, WarehouseServices warehouseServices,
                               RepresentanteServices representanteServices, ProductService productService){
        this.inBoundOrderRepository = inBoundOrderRepository;
        this.warehouseServices = warehouseServices;
        this.representanteServices = representanteServices;
        this.productService = productService;
    }


    public void registra(InBoundOrder inBoundOrder){
        List<BatchStock> batchStocks = inBoundOrder.getBatchStock();

        batchStocks.forEach(b -> {
            b.setInboundOrder(inBoundOrder);
            b.getBatchStockItem().setBatchStock(b);
        });
        try{

                this.inBoundOrderRepository.save(inBoundOrder);

        }catch(RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException( "deu ruim");
        }
    }

    public InBoundOrderRequestDTO validInboundOrder(InBoundOrderRequestDTO inb){

        this.warehouseServices.obterWarhouseByCode(inb.getSectionForInboundDTO().getWarehouseCode());
        this.sectionServices.obterSectionByCode(inb.getSectionForInboundDTO().getCode());
        this.representanteServices.obterRepresentanteById(inb.getRepresentanteId());
        this.representanteIsPresenteWarehouse(inb.getRepresentanteId());
       this.sectionMatchStockType(inb.getSectionForInboundDTO().getCode());
       this.sectionHasCapacity(inb);
       this.validProductInboud(inb);


        return inb;
    }

    public boolean  representanteIsPresenteWarehouse(Long id){
        for (Section sec : sectionServices.listaSection()){
            if (sec.getWarehouse().getRepresentante().getId() == id){
                Representante representante = sec.getWarehouse().getRepresentante();
                return representante != null; // return true TODO
            }else throw new RuntimeException("representante não pertence ao armazem");
        }
        return false;
    }

    private boolean sectionMatchStockType(Long code) {
        StockType stockType = sectionServices.obtemTypeStockSection(code);
        List<Section> listsec = sectionServices.listaSection();
        for (Section section : listsec ) {
            if (section.getStockType().equals(stockType)) {
                return true;
            }
        }
        throw new RuntimeException("section  não corresponde ao tipo de produto");
    }


    private boolean validProductInboud(InBoundOrderRequestDTO inb){
        List<Long> listBtcItemID = inb.getBatchStockDTOList().stream()
                .map(batchStockRequestDTO -> batchStockRequestDTO.getBatchStockItem()).collect(Collectors.toList());

        List<Long> listProductID = productService.listProductId();
        if(listProductID.contains(listBtcItemID)){
            return true;

        }throw new RuntimeException("Produto nao existe");

    }





    private boolean sectionHasCapacity(InBoundOrderRequestDTO inb){
        //capacidade da section by code
        int capacitySection = sectionServices.obtemQuantidadeDoSection(inb.getSectionForInboundDTO().getCode());

        //soma de valores da lista de batchstock
        int sumOfProductQuantity = inb.getBatchStockDTOList()
                .stream().mapToInt(value -> value.getQuantity()).sum();

        if (capacitySection >= sumOfProductQuantity){
            return true;
        }
        throw new RuntimeException("Este setor contém uma capacidade de " + capacitySection +
                " lotes e voce está inserindo: " + sumOfProductQuantity + "  lotes");
    }


    public InBoundOrder convertedto(RepresentanteServices representanteServices, SectionServices sectionServices,
                                    ProductService productService, SellerService sellerService){

        Section section = sectionServices.obterSectionByCode(inb.getSectionForInboundDTO().getCode());
        try{
            InBoundOrder inboundOrder = null;
            inboundOrder = InBoundOrder.builder()
                    .orderDate(inb.getOrderDate())
                    .representative(representanteServices.obter(inb.getRepresentanteId()))
                    .orderNumber(inb.getOrderNumber())
                    .section(section)
                    .batchStock(converte(inb.getBatchStockDTOList(), productService, sellerService)).build();


            return inboundOrder;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<BatchStock> converte(List<BatchStockRequestDTO> dtos, ProductService productService, SellerService sellerService){
        List<BatchStock> resultList = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (BatchStockRequestDTO dto: dtos) {
            BatchStock batchStock = null;
            batchStock = BatchStock.builder()
                    .batchStockNumber(dto.getBatchStockNumber())
                    .dueDate(dto.getDueDate())
                    .manufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(),fmt))
                    .currentQuality(dto.getCurrentQuality())
                    .initialQuality(dto.getInitialQuality())
                    .minimumTemperature(dto.getMinimumTemperature())
                    .maximumTemperature(dto.getMaximumTemperature())
                    .currentTemperature(dto.getMaximumTemperature())
                    .seller(sellerService.obter(dto.getSellerId()))
                    .quantity(dto.getQuantity())
                    .volume(dto.getVolume())
                    .batchStockItem(
                            BatchStockItem.builder()
                                    .maximumTemperature(dto.getMaximumTemperature())
                                    .quantity(dto.getQuantity())
                                    .volume(dto.getVolume())
                                    .product(productService.obtem(dto.getBatchStockItem()))
                                    .maximumTemperature(dto.getMinimumTemperature())
                                    .build()
                    )
                    .build();
            resultList.add(batchStock);
        }
        return resultList;
    }

}

