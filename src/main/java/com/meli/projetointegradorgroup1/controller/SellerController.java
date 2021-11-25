package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.services.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Cadastrar novo Seller")
    public ResponseEntity<Object>createSeller(@Valid @RequestBody SellerRequestDTO sellerRequestDTO, UriComponentsBuilder uriBuilder) {
        sellerService.validaCpf(sellerRequestDTO.getCpf());
        Seller seller = sellerService.convert(sellerRequestDTO);
        return sellerService.save(seller, uriBuilder);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de Sellers")
    List<SellerResponseDTO> getSellerList() {
        return sellerService.getSellers();
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "Retornar Seller único a partir do id")
    public SellerResponseDTO getSellerById(@PathVariable("id") Long id) {
        return sellerService.convertToDto(sellerService.obtem(id));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar Seller a partir do id")
    public ResponseEntity<Object> updateSeller(@PathVariable("id") Long id, @Valid @RequestBody SellerRequestDTO sellerRequestDTO, UriComponentsBuilder uriBuilder) {
        Seller sellerFind = sellerService.obtem(id);
        Seller seller = sellerService.validaUpdate(sellerFind, sellerRequestDTO);
        return sellerService.save(seller, uriBuilder);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar Seller a partir do id")
    public SellerResponseDTO deleteSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerService.obtem(id);
        sellerService.deleta(id);
        return sellerService.convertToDto(seller);
    }

}