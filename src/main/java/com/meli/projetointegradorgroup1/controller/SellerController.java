package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    public SellerController(SellerRepository sellerRepository, SellerService sellerService) {
        this.sellerService = sellerService;
    }


    //Cadastrar vendedor - ok
    @PostMapping("/create")
    public Seller createSeller(@RequestBody Seller seller){

        return sellerService.setSeller(seller);
    }


    //Consultar lista de  vende
    // dores
    @GetMapping("/list") // - ok
    public List<SellerResponseDTO> getSellerList() {

        return sellerService.getSellers();
    }

    //busca vendedor pelo id
    @GetMapping("/find/{id}") // - ok
    public Seller getSellerById(@PathVariable("id") Long id) {
        return sellerService.findSellerById(id);

    }

    // atualizando vendedor pelo ID -  ok
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateSeller(@PathVariable("id") Long id, @Valid @RequestBody Seller seller) {

        if(sellerService.findSellerById(id) != null) {
            return sellerService.update(seller, id);
        }
        throw new RuntimeException("Representante não encontrado");
    }

    //delete todos vendedores - ok
    @DeleteMapping("/deleteall")
    public ResponseEntity<HttpStatus> deleteAllSellers() {
        return sellerService.delAllSellers();

    }

    //deletar vendedor pelo ID - ok
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSellerById(@PathVariable("id") Long id) {
        //// delete
        return sellerService.delSeller((id));
    }



}