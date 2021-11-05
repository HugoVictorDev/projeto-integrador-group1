package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.RepresentativeDTO;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/representative")
public class RepresantiveController {

    @Autowired
    RepresentanteRepository representanteRepository;
    @Autowired
    RepresentativeServices representativeServices;


    //Cadastrar representante
    @PostMapping("/post")
    public RepresentativeDTO createRepresentative (@Valid @RequestBody RepresentativeDTO representativedto){
           representativeServices.valida(representativedto);
           Representante representative = RepresentativeDTO.converte(representativedto);
           return RepresentativeDTO.converte(representanteRepository.save(representative));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentativeDTO> getRepresentativeList() {
           return RepresentativeDTO.converte(representativeServices.listaRepresentative());
    }

    //Atualizar por id
    @PutMapping("/update/{id}")
    public RepresentativeDTO updateRepresentative(@PathVariable("id") Long id,@Valid @RequestBody RepresentativeDTO representativedto) {
           Optional<Representante> representativeFind = representanteRepository.findById(id);
           Representante representative = representativeServices.validaUpdate(representativeFind, representativedto);
           return RepresentativeDTO.converte(representanteRepository.save(representative));
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentativeDTO getRepresentativeById(@PathVariable("id") Long id) {
           Optional<Representante> representativeFind = representanteRepository.findById(id);
           return RepresentativeDTO.converte(representativeServices.findRepresentative(representativeFind));
    }


    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentativeDTO deleteRepresentativeById(@PathVariable("id") Long id) {
           Representante representative = representativeServices.findRepresentative(representanteRepository.findById(id));
           representanteRepository.deleteById(id);
           return RepresentativeDTO.converte(representative);
        }
    }

