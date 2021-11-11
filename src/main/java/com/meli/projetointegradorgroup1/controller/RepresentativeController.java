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
public class RepresentativeController {

    @Autowired
    RepresentanteServices RepresentativeServices;



    //Cadastrar Representative
//    @PostMapping("/post")
//    public RepresentanteDTO createRepresentative (@Valid @RequestBody RepresentanteDTO representativedto){//
//           RepresentativeServices.(representativedto);
//           Representative representative = RepresentanteDTO.converte(representativedto);
//           return RepresentanteDTO.converte(RepresentativeServices.save(representative));
//    }

    //Consultar lista de  representantes
//    @GetMapping("/list")
//   public List<RepresentanteDTO> getRepresentativeList() {
//           return RepresentanteDTO.converte(RepresentativeServices.findAll());
//    }

    //Atualizar por id
//    @PutMapping("/update/{id}")
//    public RepresentanteDTO updateRepresentative(@PathVariable("id") Long id, @Valid @RequestBody RepresentanteDTO representativedto) {
//           Optional<Representative> representativeFind = RepresentativeServices.findById(id);
      //     Representative representative = RepresentativeServices.validaUpdate(representativeFind, representativedto);
//           return RepresentanteDTO.converte(RepresentativeServices.save(representative));
//    }


    //consultar representatnte pelo ID
//    @GetMapping("{id}")
//    public RepresentanteDTO getRepresentativeById(@PathVariable("id") Long id) {
//           Optional<Representative> representativeFind = RepresentativeServices.findById(id);
//           return RepresentanteDTO.converte(RepresentativeServices.findRepresentative(representativeFind));
//    }


    //deletar representatnte pelo ID
   // @DeleteMapping("/delete/{id}")
    //public RepresentanteDTO deleteRepresentativeById(@PathVariable("id") Long id) {
    //       Optional<Representative> representative = RepresentativeServices.findById(id);
    //       RepresentativeServices.deleteById(id);
    //       return RepresentanteDTO.converte(representative);
    //    }
    }

