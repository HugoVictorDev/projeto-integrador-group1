package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;

import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inboundorder")
public class InBoundOrderController {

    @Autowired
    InBoundOrderRepository inBoundOrderRepository;

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
    RepresentativeServices representativeServices;



    @PostMapping("/create")
    public InBoundOrderRequestDTO create(@RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO) {
        sectionServices.validSectionExist(inBoundOrderRequestDTO.getSectionForInboundDTO());
        sectionServices.validWarhouseExist(inBoundOrderRequestDTO.getSectionForInboundDTO());
        this.inBoundOrderRepository.save(inBoundOrderRequestDTO.convertedto(representativeServices, sectionServices, productService));
        return inBoundOrderRequestDTO;
    }



}
