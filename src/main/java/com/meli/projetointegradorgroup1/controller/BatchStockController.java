package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.InBoundOrderDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.service.InboundOrderServices;
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
    private InboundOrderServices inboundOrderServices;


    @GetMapping("/list")
    public Iterable<BatchStock> listInbound(){
        return inboundOrderRepository.findAll();
    }

    public Iterable<Representative> listRepresentative(){
        return representativeRepository.findAll();
    }

    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/inboundorder/{representativeId")
    public ResponseEntity<InBoundOrderDTO> createInbound (@PathVariable("representativeId") Long representativeId, @RequestBody InBoundOrder inBoundOrder){
        InBoundOrder _inBoundOrder  = inboundOrderServices.saveIBO(inBoundOrder,representativeId);
        InBoundOrderDTO _inboundOrderDTO = InBoundOrderDTO.converte(_inBoundOrder);
        return new ResponseEntity<>(_inboundOrderDTO, HttpStatus.CREATED);
    }


    //deletar Inbound pelo ID
    @DeleteMapping("/delete/{ordernumber}")
    public ResponseEntity<HttpStatus> deleteInboundOrderByOrderNumber(@PathVariable("ordernumber") Long ordernumber) {
        inboundOrderServices.deleteIBO(ordernumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update Inbound pelo ID
    @PutMapping("/inboundorder/")
    public ResponseEntity<HttpStatus> updateOrderByOrderNumber(@RequestBody InBoundOrder inBoundOrder) {

        //InBoundOrder inBoundOrder = new InBoundOrder();
        inBoundOrder = inboundOrderServices.saveIBO(inBoundOrder,inBoundOrder.getRepresentative().getRepresentative_Id());

        Optional<InBoundOrder> _inboundOrder = inboundOrderRepository.findById(inBoundOrder.getOrderNumber());
        if (_inboundOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
