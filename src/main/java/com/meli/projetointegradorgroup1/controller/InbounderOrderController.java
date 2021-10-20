package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.entity.InbounderOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InbounderOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/inbounder")
public class InbounderOrderController {

    @Autowired
    private InbounderOrderRepository inbounderOrderRepository;
    private RepresentativeRepository representativeRepository;

    @GetMapping("/list")
    public Iterable<InbounderOrder> listInbounder(){ return inbounderOrderRepository.findAll(); }

    public Iterable<Representative> listRepresentative(){ return representativeRepository.findAll(); }

    //Cadastro do Inbound
    //Necessario a criação do Representative antes
    @PostMapping("/create/{representativeId")
    public ResponseEntity<InbounderOrder> createInbound (@PathVariable("representativeId") Long representativeId, @RequestBody InbounderOrder inbounderOrder){
        Representative representative = new Representative(representativeRepository.findById(representativeId));
        try {
            InbounderOrder _inboundOrder = inbounderOrderRepository.save(new InbounderOrder( inbounderOrder.getOrderNumber() , representative, inbounderOrder.getOrderDate()));
            return new ResponseEntity<>(_inboundOrder, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRepresentativeById(@PathVariable("id") Long id) {
        try {
            inbounderOrderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
