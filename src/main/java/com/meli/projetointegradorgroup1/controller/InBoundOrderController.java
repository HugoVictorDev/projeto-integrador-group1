package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequestDTO;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    public InBoundOrderController(InBoundOrderService inBoundOrderService) {
        this.inBoundOrderService = inBoundOrderService;
    }


    @PostMapping("/create")
    public ResponseEntity<Object>create(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO, UriComponentsBuilder uriBuilder) {
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        return inBoundOrderService.registra(uriBuilder, inBoundOrderRequestDTO, (inBoundOrderRequestDTO.convertedto(representanteServices,
                sectionServices, productService, sellerService)));
    }


    @PutMapping("/update")
    public  InBoundOrderRequestDTO update(@Valid @RequestBody InBoundOrderRequestDTO inBoundOrderRequestDTO){
        this.inBoundOrderService.validInboundOrder(inBoundOrderRequestDTO);
        this.inBoundOrderService.updateInbound(inBoundOrderRequestDTO);
        return inBoundOrderRequestDTO;

    }
}
