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

    public WarehouseController(WarehouseRepository warehouseRepository, WarehouseServices warehouseServices) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseServices = warehouseServices;
    }

    //criar warehouse
    @PostMapping("/create")
    public WarehouseDTO createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
       Warehouse warehouse = warehouseServices.converte(warehouseDTO);
       return warehouseServices.converteToDto(warehouseRepository.save(warehouse));
        }

    //listar warehouses
    @GetMapping("/list")
    public Iterable<WarehouseDTO> list(WarehouseDTO warehouseDTO){
       return warehouseServices.converteList(warehouseServices.listaWarehouse());

    }

    //buscar warehouse por id
    @GetMapping("/list/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable("id") Long id){
       return warehouseServices.converteToDto(warehouseServices.obterWarehouse(id));

    }

    //atualizar por id
    @PutMapping("/update")
    public WarehouseDTO updateWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
           Optional<Warehouse> warehouseFind = warehouseRepository.findById(warehouseDTO.getWarehouseId());
           Warehouse warehouse = warehouseServices.validaUpdate(warehouseFind, warehouseDTO);
           return warehouseServices.converteToDto(warehouseRepository.save(warehouse));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public WarehouseDTO  deleteWarehouseById(@PathVariable("id") Long id){
           Warehouse warehouse = warehouseServices.obterWarehouse(id);
           warehouseServices.deleta(id);
           return warehouseServices.converteToDto(warehouse);
    }

}
