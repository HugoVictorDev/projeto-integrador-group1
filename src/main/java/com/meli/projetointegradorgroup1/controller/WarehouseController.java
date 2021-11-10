package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.response.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {


    @Autowired
    private WarehouseServices warehouseServices;

    public WarehouseController(WarehouseServices warehouseServices) {
        this.warehouseServices = warehouseServices;
    }

    //criar warehouse
    @PostMapping("/create")
    public WarehouseDTO createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
       Warehouse warehouse = warehouseServices.converte(warehouseDTO);
       return warehouseServices.converteToDto(warehouseServices.save(warehouse));
        }

    //listar warehouses
    @GetMapping("/list")
    public Iterable<WarehouseDTO> list(){
       return warehouseServices.converteList(warehouseServices.listaWarehouse());
    }

    //buscar warehouse por id
    @GetMapping("/list/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable("id") Long id){
       return warehouseServices.converteToDto(warehouseServices.obterWarhouseByCode(id));
    }

    //atualizar por id
    @PutMapping("/update/{id}")
    public WarehouseDTO updateWarehouse(@PathVariable("id") Long id, @Valid @RequestBody WarehouseDTO warehouseDTO){
           Warehouse warehouseFind = warehouseServices.obterWarehouseById(id);
           Warehouse warehouse = warehouseServices.validaUpdate(Optional.ofNullable(warehouseFind), warehouseDTO);
           return warehouseServices.converteToDto(warehouseServices.save(warehouse));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public WarehouseDTO  deleteWarehouseById(@PathVariable("id") Long id){
           Warehouse warehouse = warehouseServices.obterWarehouseById(id);
           warehouseServices.deleta(id);
           return warehouseServices.converteToDto(warehouse);
    }

}
