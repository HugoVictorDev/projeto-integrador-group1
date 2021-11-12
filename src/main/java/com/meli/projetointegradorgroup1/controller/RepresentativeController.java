package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.RepresentanteDTO;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Representante")
public class RepresentativeController {

    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    RepresentanteServices RepresentanteServices;


    //Cadastrar Representante
    @PostMapping("/post")
    public RepresentanteDTO createRepresentative (@Valid @RequestBody RepresentanteDTO RepresentanteDTO){
           //RepresentanteServices.valida(RepresentanteDTO);
//           Representante Representante = RepresentanteDTO.converte(RepresentanteDTO);
//           return RepresentanteDTO.converte(representativeRepository.save(Representante));
        return null;
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentanteDTO> getRepresentativeList() {
//           return RepresentanteDTO.converte(RepresentanteServices.listaRepresentative());
        return null;
    }

    //Atualizar por id
    @PutMapping("/update/{id}")
    public RepresentanteDTO updateRepresentative(@PathVariable("id") Long id, @Valid @RequestBody RepresentanteDTO RepresentanteDTO) {
//           Optional<Representante> representativeFind = representativeRepository.findById(id);
//           Representante Representante = RepresentanteServices.validaUpdate(representativeFind, RepresentanteDTO);
//           return RepresentanteDTO.converte(representativeRepository.save(Representante));
        return null;
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentanteDTO getRepresentativeById(@PathVariable("id") Long id) {
//           Optional<Representante> representativeFind = representativeRepository.findById(id);
//           return RepresentanteDTO.converte(RepresentanteServices.findRepresentative(representativeFind));
        return null;
    }


    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentanteDTO deleteRepresentativeById(@PathVariable("id") Long id) {
//           Representante Representante = RepresentanteServices.findRepresentative(representativeRepository.findById(id));
//           representativeRepository.deleteById(id);
//           return RepresentanteDTO.converte(Representante);
        return null;
        }
    }

