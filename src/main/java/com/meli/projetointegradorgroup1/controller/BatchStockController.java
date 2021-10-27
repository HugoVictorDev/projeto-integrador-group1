package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.BatchStockDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import com.meli.projetointegradorgroup1.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class BatchStockController {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    RepresentativeRepository representativeRepository;
    //@Autowired
    //private InBoundOrderDTO inBoundOrderDTO;
    //@Autowired
    private InboundOrderService inboundOrderService;
    private BatchStockService batchStockService;
    private BatchStockRepository batchStockRepository ;

    @GetMapping("/batchstock/list")
    public Iterable<BatchStock> listBastchStock(){
        return batchStockRepository.findAll();
    }


    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/batchstock/")
    public ResponseEntity<BatchStockDTO> createBatchStock (@RequestBody BatchStock batchStock){
        BatchStock _batchStock  = batchStockService.saveBS(batchStock);
        BatchStockDTO _batchStockDTO = BatchStockDTO.converte(_batchStock);
        return new ResponseEntity<>(_batchStockDTO, HttpStatus.CREATED);
    }


    //deletar Inbound pelo ID
    @DeleteMapping("/delete/{ordernumber}")
    public ResponseEntity<HttpStatus> deleteBatchStockNumber(@PathVariable("ordernumber") Long ordernumber) {
        batchStockService.deleteBS(ordernumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update Inbound pelo ID
    @PutMapping("/batchstock/")
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
