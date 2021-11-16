package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        this.representanteServices.obterRepresentanteById(inb.getRepresentanteId());
        this.representanteIsPresenteWarehouse(inb.getRepresentanteId());
       this.sectionMatchStockType(inb.getSectionForInboundDTO().getCode());
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
        List<Section> listsec = sectionServices.listsec();
        for (Section section : listsec ) {
            if (section.getStockType().equals(stockType)) {
                return true;
            }
        }
        return false;
    }




}

