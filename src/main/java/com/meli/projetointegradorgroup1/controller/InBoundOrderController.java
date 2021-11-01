package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.InBoundOrderRequest;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;

import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.InBoundOrderService;
import com.meli.projetointegradorgroup1.services.SectionServices;
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



    @PostMapping("/create")
    public InBoundOrderRequest create(@Valid @RequestBody InBoundOrderRequest inBoundOrderRequest) {
        sectionServices.validSectionExist(inBoundOrderRequest.getSectionDTOHugo());
        sectionServices.validWarhouseExist(inBoundOrderRequest.getSectionDTOHugo());
        this.inBoundOrderRepository.save(inBoundOrderRequest.convertedto());
        return inBoundOrderRequest;
    }


//    //Consultar lista de  vendedores
//    @GetMapping("/list")
//    List<BatchStockItemResponseDTO> getList() {
//        return batchStockItemService.getBatchStockItemsList();
//    }
//
//    //busca vendedor pelo id
//    @GetMapping("{id}")
//    public BatchStockItemResponseDTO getBatchStockItemById(@PathVariable("id") Long id) {
//        return batchStockItemService.convertEntityToDTO(batchStockItemRepository.getById(id));
////
//    }
//
//    // atualizando vendedor pelo ID
//    @PutMapping("/update/{id}")
//    public BatchStockItem updateBatchStockItemID(@PathVariable("id") Long id, @RequestBody BatchStockItem batchStockItem) {
//        Optional<BatchStockItem> batchStockItemFind = batchStockItemRepository.findById(id);
//        BatchStockItem _bat = batchStockItemService.validaUpdate(batchStockItemFind, batchStockItem);
//        return batchStockItemRepository.save(_bat);
//
//    }
//
//    //delete todos vendedores
//    @DeleteMapping("/deleteall")
//    public BatchStockItem deleteAllBatchStockItems() {
//        batchStockItemRepository.deleteAll();
//        return null;
//
//    }
//
//    //deletar vendedor pelo ID
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<HttpStatus> deleteBatchStockItemById(@PathVariable("id") Long id) {
//        try {
//            batchStockItemRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
