package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }




    //Cadastrar vendedor - ok
    @PostMapping("/create")
    public Seller createSeller(@RequestBody Seller seller, UriComponentsBuilder uriBuilder){

        return sellerService.setSeller(seller,uriBuilder);
    }


    //Consultar lista de  vendedores
    @GetMapping("/list") // - ok
    public List<SellerResponseDTO> getSellerList() {

        return sellerService.getSellers(inb.getSellerId());
    }

    //busca vendedor pelo id
    @GetMapping("/find/{id}") // - ok
    public Seller getSellerById(@PathVariable("id") Long id) {
        return sellerService.findSellerById(id);

    }

    // atualizando vendedor pelo ID -  ok
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus>  updateSeller(@Valid @RequestBody Seller seller) {
            return sellerService.update(seller);
    }



    //deletar vendedor pelo ID - ok
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSellerById(@PathVariable("id") Long id) {
        //// delete
        return sellerService.delSeller((id));
    }



}