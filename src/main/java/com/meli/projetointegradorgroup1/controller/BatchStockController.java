package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.BatchStockDTO;

import com.meli.projetointegradorgroup1.dto.request.BatchStockDTOhugo;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/batchstock")
public class BatchStockController {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    private BatchStockService batchStockService;
    @Autowired
    private BatchStockRepository batchStockRepository ;

    private BatchStockItem batchStockItem;

    private BatchStockDTOhugo batchStockDTOhugo;


    @PostMapping("/create")
    public BatchStockDTOhugo createBatchStock ( @RequestBody BatchStockDTOhugo batchStockDTOhugo){
           batchStockService.valida(batchStockDTOhugo.getBatchStockItem());
            this.batchStockRepository.save(BatchStockDTOhugo.convertedto(batchStockDTOhugo, batchStockItem));
            return batchStockDTOhugo;
    }

    @GetMapping("/list")
    public List<BatchStockResponseDTO> listBastchStock(){
           return batchStockService.findBatchSotck();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBatchStockNumber(@PathVariable("id") Long ordernumber) {
        batchStockService.deleteBS(ordernumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id")
    public ResponseEntity<HttpStatus> updateBatchStockNumber(@RequestBody BatchStock batchStock) {

        //InBoundOrder inBoundOrder = new InBoundOrder();
        batchStock = batchStockService.saveBS(batchStock);

        Optional<InBoundOrder> _batchStock = inboundOrderRepository.findById(batchStock.getBatchStockNumber());
        if (_batchStock.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
