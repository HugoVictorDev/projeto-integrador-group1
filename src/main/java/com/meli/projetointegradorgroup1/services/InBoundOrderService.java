package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
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
    public InBoundOrderService(InBoundOrderRepository inBoundOrderRepository, WarehouseServices warehouseServices){
        this.inBoundOrderRepository = inBoundOrderRepository;
        this.warehouseServices = warehouseServices;
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
        return inb;
    }

}

