package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;

import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inboundorder")
public class InBoundOrderController {

    @Autowired
    InBoundOrderService inBoundOrderService;

    @Autowired
    SectionServices sectionServices;
    //Cadastrar BatchStockItem

    @Autowired
    BatchStockService batchStockService;

    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @Autowired
    RepresentanteServices representanteServices;

    @Autowired
    SellerService sellerService;

    @Autowired
    WarehouseServices warehouseServices;


    @PostMapping("/create")
    public InBoundOrderRequestDTO create(@RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO) {
//        this.warehouseServices.obterWarhouseByCode(inBoundOrderRequestDTO.getSectionForInboundDTO().getWarehouseCode());
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        this.inBoundOrderService.registra(inBoundOrderRequestDTO.convertedto(representanteServices,
                sectionServices, productService, sellerService));
        return inBoundOrderRequestDTO;
    }


}
