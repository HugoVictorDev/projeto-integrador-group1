package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.entity.InbounderOrder;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.InbounderOrderRepository;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/inbounder")
public class InbounderOrderController {

    @Autowired
    private InbounderOrderRepository inbounderOrderRepository;

    @GetMapping("/list")
    public Iterable<InbounderOrder> list(){ return inbounderOrderRepository.findAll(); }

    //Cadastro do Inbound
    @PostMapping("/create")
    public ResponseEntity<InbounderOrder> createInbound (@RequestBody InbounderOrder inbounderOrder){
        try {
            InbounderOrder _inboundOrder = inbounderOrderRepository.save(new InbounderOrder(inbounderOrder.getOrderNumber() , inbounderOrder.getRepresentative(), inbounderOrder.getOrderDate()));
            return new ResponseEntity<>(_inboundOrder, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRepresentativeById(@PathVariable("id") Long id) {
        try {
            inbounderOrderRepository.deleteById(String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
