package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/representante")
public class RepresentanteController {

    @Autowired
    RepresentanteServices representanteServices;

    public RepresentanteController(RepresentanteServices representanteServices) {
        this.representanteServices = representanteServices;
    }

    //Cadastrar representante
    @PostMapping("/post")
    public ResponseEntity<Object>createrepResentante(@Valid @RequestBody RepresentanteDTO representanteDTO, UriComponentsBuilder uriBuilder){
           representanteServices.valida(representanteDTO);
           Representante representante = representanteServices.convert(representanteDTO);
           return representanteServices.save(representante, uriBuilder);
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentanteDTO>getRepresentanteList() {
           return representanteServices.convertList(representanteServices.listaRepresentante());
    }

    //Atualizar por id
    @PutMapping("/update")
    public ResponseEntity<Object>updateRepresentante(@Valid @RequestBody RepresentanteDTO representanteDTO, UriComponentsBuilder uriBuilder) {
           Representante representanteFind = representanteServices.obter(representanteDTO.getRepresentatne_Id());
           Representante representante = representanteServices.validaUpdate(representanteFind, representanteDTO);
           return representanteServices.save(representante, uriBuilder);
    }

    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentanteDTO getRepresentanteById(@PathVariable("id") Long id) {
           Representante representatne = representanteServices.obter(id);
           return representanteServices.convertToDto(representatne);
    }

    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentanteDTO deleteRepresentanteById(@PathVariable("id") Long id){
           Representante representante  = representanteServices.obter(id);
           representanteServices.deletaRepresentante(id);
           return representanteServices.convertToDto(representante);
        }
    }

