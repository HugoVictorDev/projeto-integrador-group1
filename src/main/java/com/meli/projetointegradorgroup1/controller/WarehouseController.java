package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/armazem")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping("/criar")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){
        try {
            Warehouse newWarehouse = warehouseRepository.save(new Warehouse(warehouse.getName(), warehouse.getAddress(), warehouse.getSize()));
            return new ResponseEntity<>(newWarehouse, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
