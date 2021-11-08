package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.RepresentanteDTO;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/representative")
public class RepresentanteController {

    @Autowired
    RepresentanteRepository representanteRepository;
    @Autowired
    RepresentanteServices representanteServices;


    //Cadastrar representante
    @PostMapping("/post")
    public RepresentanteDTO createRepresentative (@Valid @RequestBody RepresentanteDTO representativedto){
//           representanteServices.valida(representativedto); TODO revisar
           Representante representative = RepresentanteDTO.converte(representativedto);
           return RepresentanteDTO.converte(representanteRepository.save(representative));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentanteDTO> getRepresentativeList() {
           return RepresentanteDTO.converte(representanteServices.listaRepresentative());
    }

    //Atualizar por id
    @PutMapping("/update/{id}")
    public RepresentanteDTO updateRepresentative(@PathVariable("id") Long id, @Valid @RequestBody RepresentanteDTO representativedto) {
           Optional<Representante> representativeFind = representanteRepository.findById(id);
           Representante representative = representanteServices.validaUpdate(representativeFind, representativedto);
           return RepresentanteDTO.converte(representanteRepository.save(representative));
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentanteDTO getRepresentativeById(@PathVariable("id") Long id) {
           Optional<Representante> representativeFind = representanteRepository.findById(id);
           return RepresentanteDTO.converte(representanteServices.findRepresentative(representativeFind));
    }


    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentanteDTO deleteRepresentativeById(@PathVariable("id") Long id) {
           Representante representative = representanteServices.findRepresentative(representanteRepository.findById(id));
           representanteRepository.deleteById(id);
           return RepresentanteDTO.converte(representative);
        }
    }

