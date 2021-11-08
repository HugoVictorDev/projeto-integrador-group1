package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServices {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionServices sectionServices;


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
        if (warehouse != null){
            return warehouse;
        }else throw new EntityNotFoundException("warHouse não encontrada");
    }

    public Warehouse obterWarehouse(Long warehouseID) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseID);
        if (!warehouse.isPresent()){
            throw new RuntimeException("Warehouse não encontrada");
        }
        return warehouse.get();
    }

  //  @GetMapping(value="/handler")
 //   public void handler() {
 //       throw new ArithmeticException("olha... algo serio aconteceu. fuja para as montanhas");
 //   }
    public ResponseEntity<Object> deleta(Long id) {
        try {
            warehouseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
  //          throw new ConstraintViolationException("olha... algo serio aconteceu. fuja para as montanhas"  );
             throw new ArithmeticException("olha... algo serio aconteceu. fuja para as montanhas");
        }
    }
}
