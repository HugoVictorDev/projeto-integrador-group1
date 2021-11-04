package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/representative")
public class RepresantiveController {

    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    RepresentativeServices representativeServices;


    //Cadastrar representante
    @PostMapping("/post")
    @ApiOperation(value = "Cadastrar novo representative")
    public RepresentativeDTO createRepresentative (@Valid @RequestBody RepresentativeDTO representativedto){
           representativeServices.valida(representativedto);
           Representative representative = RepresentativeDTO.converte(representativedto);
           return RepresentativeDTO.converte(representativeRepository.save(representative));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de representative")
    public List<RepresentativeDTO> getRepresentativeList() {
           return RepresentativeDTO.converte(representativeServices.listaRepresentative());
    }

    //Atualizar por id
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar representative a partir do id")
    public RepresentativeDTO updateRepresentative(@PathVariable("id") Long id,@Valid @RequestBody RepresentativeDTO representativedto) {
           Optional<Representative> representativeFind = representativeRepository.findById(id);
           Representative representative = representativeServices.validaUpdate(representativeFind, representativedto);
           return RepresentativeDTO.converte(representativeRepository.save(representative));
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    @ApiOperation(value = "Retornar representative único a partir do id")
    public RepresentativeDTO getRepresentativeById(@PathVariable("id") Long id) {
           Optional<Representative> representativeFind = representativeRepository.findById(id);
           return RepresentativeDTO.converte(representativeServices.findRepresentative(representativeFind));
    }


    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar representative a partir do id")
    public RepresentativeDTO deleteRepresentativeById(@PathVariable("id") Long id) {
           Representative representative = representativeServices.findRepresentative(representativeRepository.findById(id));
           representativeRepository.deleteById(id);
           return RepresentativeDTO.converte(representative);
        }
    }

