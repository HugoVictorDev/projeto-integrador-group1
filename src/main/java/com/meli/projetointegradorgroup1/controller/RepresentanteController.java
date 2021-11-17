package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;

import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public RepresentanteDTO createrepResentante (@Valid @RequestBody RepresentanteDTO representanteDTO){
           representanteServices.valida(representanteDTO);
           Representante representante = representanteServices.converte(representanteDTO);
           return representanteServices.converteToDto(representanteServices.save(representante));
    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public List<RepresentanteDTO> getRepresentanteList() {
           return representanteServices.converteList(representanteServices.listaRepresentante());
    }

    //Atualizar por id
    @PutMapping("/update")
    public RepresentanteDTO updateRepresentante(@Valid @RequestBody RepresentanteDTO representanteDTO) {
           Representante representanteFind = representanteServices.obter(representanteDTO.getRepresentatne_Id());
           Representante representante = representanteServices.validaUpdate(representanteFind, representanteDTO);
           return representanteServices.converteToDto(representanteServices.save(representante));
    }

    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public RepresentanteDTO getRepresentanteById(@PathVariable("id") Long id) {
           Representante representatne = representanteServices.obter(id);
           return representanteServices.converteToDto(representatne);
    }

    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public RepresentanteDTO deleteRepresentanteById(@PathVariable("id") Long id){
           Representante representante  = representanteServices.obter(id);
           representanteServices.deletaRepresentante(id);
           return representanteServices.converteToDto(representante);
        }
    }

