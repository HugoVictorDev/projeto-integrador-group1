package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.dto.RepresentativeResponseDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public RepresentativeDTO createRepresentative (@Valid @RequestBody RepresentativeDTO representativedto){
           representativeServices.valida(representativedto);
           Representative representative = RepresentativeDTO.converte(representativedto);
           return RepresentativeDTO.converte(representativeRepository.save(representative));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentativeResponseDTO> getRepresentativeList() {
           List<Representative> representative = new ArrayList<>();
           representativeRepository.findAll().forEach(representative::add);
           return RepresentativeResponseDTO.converte(representative);
    }


    @PutMapping("/update/{id}")
    public RepresentativeDTO updateRepresentative(@PathVariable("id") Long id,@Valid @RequestBody RepresentativeDTO representativedto) {
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

