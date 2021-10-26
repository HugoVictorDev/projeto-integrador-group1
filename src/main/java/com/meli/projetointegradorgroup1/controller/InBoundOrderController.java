package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.dto.InBoundOrderDTO;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.persistence.InBoundOrderPersistence;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping(path = "/inbound")
public class InBoundOrderController {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    private InBoundOrderDTO inBoundOrderDTO;
    @Autowired
    private InboundOrderService inBOService;


    @GetMapping("/list")
    public Iterable<InBoundOrder> listInbound(){
        return inboundOrderRepository.findAll();
    }

    public Iterable<Representative> listRepresentative(){
        return representativeRepository.findAll();
    }

    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/create/{representativeId")
    public ResponseEntity<InBoundOrderDTO> createInbound (@PathVariable("representativeId") Long representativeId, @RequestBody InBoundOrder inBoundOrder){
        InBoundOrder _inBoundOrder  = inBOService.saveIBO(inBoundOrder,representativeId);
        InBoundOrderDTO _inboundOrderDTO = InBoundOrderDTO.converte(_inBoundOrder);
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

        Optional<InBoundOrder> _inboundOrder = inboundOrderRepository.findById(id);
        if (_inboundOrder.isPresent()) {

            InBoundOrderDTO _inboundOrderDTO = InBoundOrderDTO.converte(inBoundOrder);
            return new ResponseEntity<>(_inboundOrderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }


    //Update Inbound pelo ID
    @DeleteMapping("/update/")
    public ResponseEntity<HttpStatus> updateOrderByOrderNumber(@RequestBody InBoundOrder inboundOrder) {

        Optional<InBoundOrder> _inboundOrder = inboundOrderRepository.findById(inboundOrder.getOrderNumber());
        if (_inboundOrder.isPresent()) {
            InBoundOrder inBoundOrder = _inboundOrder.get();
            inboundOrderRepository.deleteById(inboundOrder.getOrderNumber());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}
