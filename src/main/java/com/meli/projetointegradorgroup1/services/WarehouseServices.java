package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServices {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SectionRepository sectionRepository;

    public WarehouseServices(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public void valida(Long warehouseID) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if (!warehouse.isPresent()){
            throw new RuntimeException("Warehouse não cadastrada");
        }
    }

    public List<Warehouse> listaWarehouse() {
        List<Warehouse> warehouselist = warehouseRepository.findAll();
        if(warehouselist.size() == 0){
            throw new RuntimeException("Não existem Wharehoses cadastradas");
        }return warehouselist;
    }


    public Warehouse validaUpdate(Optional<Warehouse> warehouseFind, WarehouseDTO warehouseDTO) {
    if(warehouseFind == null  || warehouseFind.equals(Optional.empty())){
        throw new RuntimeException("Warehouse não encontrada");
    }else{
        Warehouse warehouse = warehouseFind.get();
        warehouse.setName(warehouseDTO.getName());
        warehouse.setAddress(warehouseDTO.getAddress());
        warehouse.setSize(warehouseDTO.getSize());
        return warehouse;
         }
    }

    public Warehouse obterWarehouse2(Long warehouseID) {
        return warehouseRepository.findById(warehouseID).get();


    }

    public Warehouse obterWarehouse(Long warehouseID) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if (!warehouse.isPresent()){
            throw new RuntimeException("Warehouse não encontrada");
        }
        return warehouse.get();
    }


    public void deleta(Long id) {
        try {
            warehouseRepository.deleteById(id);
        } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("Referential integrity constraint violation")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }


    public WarehouseDTO converteToDto(Warehouse wareHouse) {
            return new WarehouseDTO(wareHouse.getId(),wareHouse.getName(),wareHouse.getAddress(),wareHouse.getSize());
    }

    public Iterable<WarehouseDTO> converteList(List<Warehouse> warehouses) {
            List<WarehouseDTO> listWarehouse = new ArrayList<>();
            for (Warehouse warehouse: warehouses) {
                listWarehouse.add(new WarehouseDTO(warehouse.getId(), warehouse.getName(), warehouse.getAddress(), warehouse.getSize()));
            }
            return listWarehouse;
        }

    public Warehouse converte(WarehouseDTO warehouseDTO) {
        return null;
    }
}
