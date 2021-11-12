package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.RepresentativeDTO;

import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/representative")
public class RepresentativeController {

    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    RepresentativeServices representativeServices;


    //Cadastrar Representative
    @PostMapping("/post")
    public RepresentativeDTO createRepresentative (@Valid @RequestBody RepresentativeDTO representativedto){
           representativeServices.valida(representativedto);
           Representative representative = RepresentativeDTO.converte(representativedto);
           return RepresentativeDTO.converte(representativeRepository.save(representative));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentativeDTO> getRepresentativeList() {
           return RepresentativeDTO.converte(representativeServices.listaRepresentative());
    }

    //Atualizar por id
    @PutMapping("/update/{id}")
    public RepresentativeDTO updateRepresentative(@PathVariable("id") Long id, @Valid @RequestBody RepresentativeDTO representativedto) {
           Optional<Representative> representativeFind = representativeRepository.findById(id);
           Representative representative = representativeServices.validaUpdate(representativeFind, representativedto);
           return RepresentativeDTO.converte(representativeRepository.save(representative));
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentativeDTO getRepresentativeById(@PathVariable("id") Long id) {
           Optional<Representative> representativeFind = representativeRepository.findById(id);
           return RepresentativeDTO.converte(representativeServices.findRepresentative(representativeFind));
    }


    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentativeDTO deleteRepresentativeById(@PathVariable("id") Long id) {
           Representative representative = representativeServices.findRepresentative(representativeRepository.findById(id));
           representativeRepository.deleteById(id);
           return RepresentativeDTO.converte(representative);
        }
    }

