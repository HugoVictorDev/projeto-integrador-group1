package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;

    //Cadastrar vendedor
    @PostMapping("/post")
    public ResponseEntity<Seller> createSeller (@RequestBody Seller seller){
        try {
            Seller _seller = sellerRepository.save(new Seller(seller.getName(), seller.getCpf(), null));
           return new ResponseEntity<>(_seller, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Consultar vendedor
    @GetMapping("/getlist")
    public ResponseEntity<List<Seller>> getAllTutorials(@RequestParam(required = false) String name) {
        try {
            List<Seller> sellers = new ArrayList<Seller>();

            if (name == null)
                sellerRepository.findAll().forEach(sellers::add);
            else
                sellerRepository.findByNameContaining(name).forEach(sellers::add);

            if (sellers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sellers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
