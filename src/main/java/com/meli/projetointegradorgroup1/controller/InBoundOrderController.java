package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.BatchStockDTO;
import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequest;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;

import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import java.util.Optional;

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
    public InBoundOrderRequest create( @RequestBody InBoundOrderRequest inBoundOrderRequest) {
        sectionServices.validSectionExist(inBoundOrderRequest.getSectionDTOHugo());
        sectionServices.validWarhouseExist(inBoundOrderRequest.getSectionDTOHugo());
        this.inBoundOrderRepository.save(inBoundOrderRequest.convertedto(representativeServices, sectionServices, productService));
        return inBoundOrderRequest;
    }



}
