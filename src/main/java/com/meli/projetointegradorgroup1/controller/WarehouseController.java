package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseServices warehouseServices;

    //criar warehouse
    @PostMapping("/create")
    public WarehouseDTO createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
           Warehouse warehouse = WarehouseDTO.converte(warehouseDTO);
           return warehouseDTO.converte(warehouseRepository.save(warehouse));
        }

    //listar warehouses
    @GetMapping("/list")
    public Iterable<WarehouseDTO> list(WarehouseDTO warehouseDTO){
           return warehouseDTO.converte(warehouseServices.listaWarehouse());
    }

    //buscar warehouse por id
    @GetMapping("/list/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable("id") Long id){
           return WarehouseDTO.converte(warehouseServices.obterWarehouse(id));
    }

    //atualizar por id
    @PutMapping("/update/{id}")
    public WarehouseDTO updateWarehouse(@PathVariable("id") Long id,@Valid @RequestBody WarehouseDTO warehouseDTO){
           Optional<Warehouse> warehouseFind = warehouseRepository.findById(id);
           Warehouse warehouse = warehouseServices.validaUpdate(warehouseFind, warehouseDTO);
           return WarehouseDTO.converte(warehouseRepository.save(warehouse));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public WarehouseDTO  deleteWarehouseById(@PathVariable("id") Long id){
           Warehouse warehouse = warehouseServices.obterWarehouse(id);
           warehouseRepository.deleteById(id);
           return WarehouseDTO.converte(warehouse);
    }
}
