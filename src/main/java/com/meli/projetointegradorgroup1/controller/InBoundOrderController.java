package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;

import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inboundorder")
public class InBoundOrderController {

    @Autowired
    InBoundOrderService inBoundOrderService;
    @Autowired
    SectionServices sectionServices;
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
    InBoundOrderRepository inBoundOrderRepository;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public InBoundOrderRequestDTO create(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO) {
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        this.inBoundOrderService.registra(inBoundOrderRequestDTO.convertedto(representanteServices,
                sectionServices, productService, sellerService));
        return inBoundOrderRequestDTO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public  InBoundOrderRequestDTO update(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO){
        this.inBoundOrderService.updateInbound(inBoundOrderRequestDTO);
        return inBoundOrderRequestDTO;

    }
}
