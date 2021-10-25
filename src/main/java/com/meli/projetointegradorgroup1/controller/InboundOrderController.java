package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.dto.InBoundOrderDTO;
import com.meli.projetointegradorgroup1.entity.InboundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InboundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping(path = "/inbound")
public class InboundOrderController {

    @Autowired
    private InboundOrderRepository inboundOrderRepository;
    @Autowired
    private RepresentativeRepository representativeRepository;

    @GetMapping("/list")
    public Iterable<InboundOrder> listInbound(){ return inboundOrderRepository.findAll(); }

    public Iterable<Representative> listRepresentative(){ return representativeRepository.findAll(); }

    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/create/{representativeId")
    public ResponseEntity<InBoundOrderDTO> createInbound (@PathVariable("representativeId") Long representativeId, @RequestBody InBoundOrderDTO inBoundOrderDTO){
        Representative representative = new Representative(representativeRepository.findById(representativeId));

        InboundOrder _inboundOrder = inboundOrderRepository.save(new InboundOrder(inBoundOrderDTO.getOrderNumber(), representative , inBoundOrderDTO.getBatchStock(), LocalDate.now()));
        InBoundOrderDTO _inboundOrderDTO = inBoundOrderDTO.converte(_inboundOrder);
        return new ResponseEntity<>(_inboundOrderDTO, HttpStatus.CREATED);
    }

    //deletar Inbound pelo ID
    @DeleteMapping("/delete/{ordernumber}")
    public ResponseEntity<HttpStatus> deleteInboundOrderByOrderNumber(@PathVariable("ordernumber") Long id) {
            inboundOrderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //pelo id
    @GetMapping("{id}")
    public ResponseEntity<InBoundOrderDTO> getInBoundOrderItemById(@PathVariable("id") Long id) {

        Optional<InboundOrder> _inboundOrder = inboundOrderRepository.findById(id);
        if (_inboundOrder.isPresent()) {
            InboundOrder inBoundOrder = _inboundOrder.get();
            InBoundOrderDTO _inboundOrderDTO = InBoundOrderDTO.converte(inBoundOrder);
            return new ResponseEntity<>(_inboundOrderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }


    //Update Inbound pelo ID
    @DeleteMapping("/update/")
    public ResponseEntity<HttpStatus> updateOrderByOrderNumber(@RequestBody InboundOrder inboundOrder) {

        Optional<InboundOrder> _inboundOrder = inboundOrderRepository.findById(inboundOrder.getOrderNumber());
        if (_inboundOrder.isPresent()) {
            InboundOrder inBoundOrder = _inboundOrder.get();
            inboundOrderRepository.deleteById(inboundOrder.getOrderNumber());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
