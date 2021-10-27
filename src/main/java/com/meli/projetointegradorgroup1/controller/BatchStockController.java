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
    public Iterable<BatchStock> listInbound(){
        return batchStockRepository.findAll();
    }


    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/batchstock/")
    public ResponseEntity<BatchStockDTO> createInbound (@RequestBody BatchStock batchStock){
        BatchStock _batchStock  = batchStockService.saveBS(batchStock);
        BatchStockDTO _batchStockDTO = BatchStockDTO.converte(_batchStock);
        return new ResponseEntity<>(_batchStockDTO, HttpStatus.CREATED);
    }


    //deletar Inbound pelo ID
    @DeleteMapping("/delete/{ordernumber}")
    public ResponseEntity<HttpStatus> deleteInboundOrderByOrderNumber(@PathVariable("ordernumber") Long ordernumber) {
        inboundOrderService.deleteIBO(ordernumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update Inbound pelo ID
    @PutMapping("/inboundorder/")
    public ResponseEntity<HttpStatus> updateOrderByOrderNumber(@RequestBody InBoundOrder inBoundOrder) {

        //InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder = inboundOrderService.saveIBO(inBoundOrder,inBoundOrder.getRepresentative().getRepresentative_Id());

        Optional<InBoundOrder> _inboundOrder = inboundOrderRepository.findById(inBoundOrder.getOrderNumber());
        if (_inboundOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
