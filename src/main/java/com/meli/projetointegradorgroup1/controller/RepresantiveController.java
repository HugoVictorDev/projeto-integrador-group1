package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
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
    public List<Representative> getRepresentativeList() {
            List<Representative> representative = new ArrayList<>();
            representativeRepository.findAll().forEach(representative::add);
            return  representative;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Representative> updateRepresentative(@PathVariable("id") Long id, @RequestBody Representative representative) {
        Optional<Representative> representativeFind = representativeRepository.findById(id);
        if (representativeFind.isPresent()) {
            Representative representattive = representativeFind.get();
            representattive.setName(representative.getName());
            representattive.setCpf(representative.getCpf());
            return new ResponseEntity<>(representativeRepository.save(representattive), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //consultar representatnte pelo ID
    @GetMapping("{id}")
    public ResponseEntity<Representative> getRepresentativeById(@PathVariable("id") Long id) {
        Optional<Representative> representativeFind = representativeRepository.findById(id);
        if (representativeFind.isPresent()) {
            return new ResponseEntity<>(representativeFind.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //deletar representatnte pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRepresentativeById(@PathVariable("id") Long id) {
            representativeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
         }
    }

