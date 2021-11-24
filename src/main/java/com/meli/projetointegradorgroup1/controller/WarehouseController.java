package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.Optional;
/**
 * @author Marco Siqueira
 */

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
    public ResponseEntity<Object>createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO, UriComponentsBuilder uriBuilder) {
        Warehouse warehouse = warehouseServices.converte(warehouseDTO);
        return warehouseServices.save(warehouse,uriBuilder);
    }

    //listar warehouses
    @GetMapping("/list")
    public Iterable<WarehouseDTO> list(){
       return warehouseServices.converteList(warehouseServices.listaWarehouse());
    }

    //buscar warehouse por id
    @GetMapping("/list/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable("id") Long id){
       return warehouseServices.convertToDto(warehouseServices.obterWarhouseByCode(id));
    }

    //atualizar por id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object>  updateWarehouse(@PathVariable("id") Long id, @Valid @RequestBody WarehouseDTO warehouseDTO, UriComponentsBuilder uriBuilder ){
           Warehouse warehouseFind = warehouseServices.obterWarehouseById(id);
           Warehouse warehouse = warehouseServices.validaUpdate(Optional.ofNullable(warehouseFind), warehouseDTO);
           return warehouseServices.save(warehouse,uriBuilder);
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public WarehouseDTO  deleteWarehouseById(@PathVariable("id") Long id){
           Warehouse warehouse = warehouseServices.obterWarehouseById(id);
           warehouseServices.deleta(id);
           return warehouseServices.convertToDto(warehouse);
    }

}
