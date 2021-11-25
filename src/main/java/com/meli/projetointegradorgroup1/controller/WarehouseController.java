package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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


    @PostMapping("/create")
    @ApiOperation(value = "Cadastrar novo Warehouse")
    public ResponseEntity<Object>createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO, UriComponentsBuilder uriBuilder) {
        Warehouse warehouse = warehouseServices.converte(warehouseDTO);
        return warehouseServices.save(warehouse,uriBuilder);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de Warehouse")
    public Iterable<WarehouseDTO> list(){
       return warehouseServices.converteList(warehouseServices.listaWarehouse());
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "Retornar Warehouse único a partir do id")
    public WarehouseDTO getWarehouseById(@PathVariable("id") Long id){
       return warehouseServices.convertToDto(warehouseServices.obterWarhouseByCode(id));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar Warehouse a partir do id")
    public ResponseEntity<Object>  updateWarehouse(@PathVariable("id") Long id, @Valid @RequestBody WarehouseDTO warehouseDTO, UriComponentsBuilder uriBuilder ){
           Warehouse warehouseFind = warehouseServices.obterWarehouseById(id);
           Warehouse warehouse = warehouseServices.validaUpdate(Optional.ofNullable(warehouseFind), warehouseDTO);
           return warehouseServices.save(warehouse,uriBuilder);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar Warehouse a partir do id")
    public WarehouseDTO  deleteWarehouseById(@PathVariable("id") Long id){
           Warehouse warehouse = warehouseServices.obterWarehouseById(id);
           warehouseServices.deleta(id);
           return warehouseServices.convertToDto(warehouse);
    }

}
