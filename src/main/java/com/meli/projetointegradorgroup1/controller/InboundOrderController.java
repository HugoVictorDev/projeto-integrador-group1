package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.entity.InboundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InboundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/inbound")
public class InboundOrderController {

    @Autowired
    private InboundOrderRepository inboundOrderRepository;
    private RepresentativeRepository representativeRepository;

    @GetMapping("/list")
    public Iterable<InboundOrder> listInbound(){ return inboundOrderRepository.findAll(); }

    public Iterable<Representative> listRepresentative(){ return representativeRepository.findAll(); }

    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/create/{representativeId")
    public ResponseEntity<InboundOrder> createInbound (@PathVariable("representativeId") Long representativeId, @RequestBody InboundOrder inboundOrder){
        Representative representative = new Representative(representativeRepository.findById(representativeId));

        InboundOrder _inboundOrder = inboundOrderRepository.save(new InboundOrder( inboundOrder.getOrderNumber() , representative, inboundOrder.getOrderDate()));
        return new ResponseEntity<>(_inboundOrder, HttpStatus.CREATED);
    }

    //deletar Inbound pelo ID
    @DeleteMapping("/delete/{ordernumber}")
    public ResponseEntity<HttpStatus> deleteInboundOrderByOrderNumber(@PathVariable("ordernumber") Long id) {
            inboundOrderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update Inbound pelo ID
    @DeleteMapping("/update/{ordernumber}")
    public ResponseEntity<InboundOrder> updateOrderByOrderNumber(@PathVariable("ordernumber") Long representativeId, @RequestBody InboundOrder inboundOrder) {

        inboundOrderRepository.deleteById(representativeId);

        Representative representative = new Representative(representativeRepository.findById(representativeId));
        InboundOrder _inboundOrder = inboundOrderRepository.save(new InboundOrder( inboundOrder.getOrderNumber() , representative, inboundOrder.getOrderDate()));

        return new ResponseEntity<>(_inboundOrder, HttpStatus.CREATED);
    }
}
