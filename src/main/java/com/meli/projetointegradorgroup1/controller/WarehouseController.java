package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseServices warehouseServices;

    // criar warehouse
    @PostMapping("/create")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
           warehouseServices.valida(warehouse);
           Warehouse _wareHouse = warehouseRepository.save(warehouse);
           return _wareHouse;
        }


    // listar warehouses
    @GetMapping("/list")
    public Iterable<Warehouse> list(){
        return warehouseRepository.findAll();
    }

    // buscar warehouse por id
    @GetMapping("/list/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") Long id){
        Optional<Warehouse> warehouseFind = warehouseRepository.findById(id);

        if (warehouseFind.isPresent()){
            return new ResponseEntity<>(warehouseFind.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // atualizar por id
    @PutMapping("/update/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") Long id, @RequestBody Warehouse warehouse){
        Optional<Warehouse> warehouseFind = warehouseRepository.findById(id);

        if (warehouseFind.isPresent()){
            Warehouse newWarehouse = warehouseFind.get();
            newWarehouse.setName(warehouse.getName());
            newWarehouse.setAddress(warehouse.getAddress());
            newWarehouse.setSize(warehouse.getSize());
            return new ResponseEntity<>(warehouseRepository.save(newWarehouse), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // deletar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteWarehouseById(@PathVariable("id") Long id){
        try {
            warehouseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
