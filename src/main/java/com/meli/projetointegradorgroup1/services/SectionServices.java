package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServices {

    @Autowired
    WarehouseServices warehouseServices;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
            WarehouseRepository warehouseRepository;


    SectionForInboundDTO sectionForInboundDTO;


    public void validarWarehouse(SectionDTO sectionDTO) {
       warehouseServices.warehouseExist(sectionDTO.getWarehouseID());
    }

//        valida warhouse
    public void validWarhouseExist(SectionForInboundDTO sectionForInboundDTO) {
        warehouseServices.warehouseExist(sectionForInboundDTO.getCode());

    }

    //    valida section
    public void validSectionExist(SectionForInboundDTO sectionForInboundDTO) {
        Optional<Section> section = sectionRepository.findById(sectionForInboundDTO.getCode());
        if (!section.isPresent()){
            throw new RuntimeException("section não cadastrada");
        }

    }


    public List<Section> listaSection() {
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.size() == 0){
            throw new RuntimeException("Não existem Sessões cadastradas");
        }return sectionList;
    }


    public Section obterSection(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (!section.isPresent()){
            throw new RuntimeException("Section não encontrada");
        }
        return section.get();
    }

    public Section obterSectionByCode(Long code) {
        Section section = sectionRepository.findByCode(code);
        if (section != null) {
           return section;
        }else throw new EntityNotFoundException("Section não encontrada");
    }


    public Section validaUpdate(Optional<Section> sectionFind, SectionDTO sectionDTO) {
        if(sectionFind.isPresent()){
            Section section = sectionFind.get();
            section.setMinimumTemperature(sectionDTO.getMinimumTemperature());
            section.setStock(sectionDTO.getStock());
            section.setStockType(sectionDTO.getStockType());
            section.setWarehouse(obterWarehouse(sectionDTO.getWarehouseID()));
            return section;
        }else{
            throw new RuntimeException("Warehouse não encontrada");
        }
}

    private Warehouse obterWarehouse(long warehouseID) {
        return warehouseServices.obterWarehouse(warehouseID);
    }

}
