package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
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
    WarehouseRepository warehouseRepository;


    //Cadastrar representante
    @PostMapping("/post")
    public ResponseEntity<RepresentativeDTO> createRepresentative (@Valid @RequestBody RepresentativeDTO representativedto){
        try {
            warehouseRepository.findById(representativedto.getWarehouseID());
            Representative representative = RepresentativeDTO.converte(representativedto);
            return new ResponseEntity<>(RepresentativeDTO.converte(representativeRepository.save(representative)), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //Consultar lista de  representantes
    @GetMapping("/list")
    public ResponseEntity<List<Representative>> getRepresentativeList() {
        try {
            List<Representative> representative = new ArrayList<>();
            representativeRepository.findAll().forEach(representative::add);
            return new ResponseEntity<>(representative, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // atualizando representatnte pelo ID
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
        try {
            representativeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
