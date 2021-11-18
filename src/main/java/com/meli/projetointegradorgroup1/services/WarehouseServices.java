package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServices {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SectionServices sectionServices;

    public WarehouseServices(WarehouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;

    }


    public boolean warehouseExist(Long warehouseCode) {
        for (Section section : sectionServices.listaSection()) {
            if (section.getWarehouse().getCode().equals(warehouseCode)){
                Warehouse warehouse = section.getWarehouse();
                return warehouse != null;
            }
        }
        return false;
    }

    public List<Warehouse> listaWarehouse() {
        List<Warehouse> warehouselist = warehouseRepository.findAll();
        if(warehouselist.size() == 0){
            throw new RuntimeException("Não existem Wharehoses cadastradas");
        }return warehouselist;
    }


   public Warehouse validaUpdate(Optional<Warehouse> warehouseFind, WarehouseDTO warehouseDTO) {
    if(warehouseFind.isPresent()){
        Warehouse warehouse = warehouseFind.get();
        warehouse.setCode(warehouseDTO.getCode());
        warehouse.setName(warehouseDTO.getName());
        warehouse.setAddress(warehouseDTO.getAddress());
        warehouse.setSize(warehouseDTO.getSize());
        return warehouse;
    }else{
        throw new RuntimeException("Warehouse não encontrada");
         }
    }

    public Warehouse obterWarhouseByCode(Long code) {
        Warehouse warehouse = warehouseRepository.findByCode(code);
        if (warehouse == null){
            throw new RuntimeException("Warehouse não encontrada");
        }return warehouse;
    }

    public Warehouse obterWarehouseById(Long warehouseID) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if (warehouse == null || warehouse.equals(Optional.empty())){
            throw new RuntimeException("Warehouse não encontrada");
        }return warehouse.get();
    }


    public Warehouse converte(WarehouseDTO dto) {
        return Warehouse.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .address(dto.getAddress())
                .size(dto.getSize())
                .build();
    }

    public Warehouse save(Warehouse warehouse) {
        try {
            warehouseRepository.save(warehouse);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação Warehouse:", e );
        }
        return warehouse;
    }

    public Iterable<WarehouseDTO> converteList(List<Warehouse> warehouses) {
        List<WarehouseDTO> listWarehouse = new ArrayList<>();
        for (Warehouse w: warehouses) {
            listWarehouse.add(
                    WarehouseDTO.builder()
                            .name(w.getName())
                            .address(w.getAddress())
                            .code(w.getCode())
                            .size(w.getSize())
                            .build()
            );
        }
        return listWarehouse;
    }

    public WarehouseDTO convertToDto(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .name(warehouse.getName())
                .address(warehouse.getAddress())
                .size(warehouse.getSize())
                .code(warehouse.getCode())
                .build();
    }


    public void deleta(Long id) {
        try {
            warehouseRepository.deleteById(id);
        } catch (RuntimeException e) {
            if (e.getCause().getCause().getMessage().contains("violates foreign key constraint ")) {
                throw new RuntimeException("violates foreign key constraint");
            } else {
                throw e;
            }
        }
    }


}
