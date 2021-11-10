package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServices {

    @Autowired
    WarehouseServices warehouseServices;

    @Autowired
    SectionRepository sectionRepository;


    SectionForInboundDTO sectionForInboundDTO;


    public SectionServices(WarehouseServices warehouseServices, SectionRepository sectionRepository) {
        this.warehouseServices = warehouseServices;
        this.sectionRepository = sectionRepository;
    }

    public void validarWarehouse(SectionDTO sectionDTO) {
        warehouseServices.valida(sectionDTO.getWarehouseID());
    }

    //        valida warhouse
    public void validWarhouseExist(SectionForInboundDTO sectionForInboundDTO) {
        warehouseServices.valida(sectionForInboundDTO.getWarehouseId());

    }

    //    valida section
    public void validSectionExist(SectionForInboundDTO sectionForInboundDTO) {
        Optional<Section> section = sectionRepository.findById(sectionForInboundDTO.getSectionId());
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


    public Optional<Section> obterSection(Long sectionID) {
        Optional<Section> section = sectionRepository.findById(sectionID);
        if (section == null){
            throw new RuntimeException("Sessão não encontrada");
        }return section;
    }

    public Section validaUpdate(Optional<Section> sectionFind, SectionDTO sectionDTO) {
        if(sectionFind == null  || sectionFind.equals(Optional.empty())){
            throw new RuntimeException("Sessão não encontrada");
        }else{
            Section section = sectionFind.get();
            section.setMinimumTemperature(sectionDTO.getMinimumTemperature());
            section.setStock(sectionDTO.getStock());
            section.setStockType(sectionDTO.getStockType());
            section.setWarehouse(obterWarehouse(sectionDTO.getWarehouseID()));
            return section;
        }
    }

    private Warehouse obterWarehouse(Long warehouseID) {
        return warehouseServices.obterWarehouse(warehouseID);
    }

    public SectionDTO convertToDto(Section section) {
        //    return new SectionDTO(section.getMinimumTemperature(), section.getStock(), section.getStockType()
        //            , Long.toString(section.getWarehouse().warehouseId()));
        return null;
    }

    public Iterable<SectionDTO> convertList(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
  /*      for (Section section: sections) {
            listaSection.add(new SectionDTO(section.getId(), section.getMinimumTemperature(), section.getStock(), section.getStockType(),
                    Long.toString(section.getWarehouse().getWarehouseId())));
        }*/
        return listaSection;
    }

    public Section convert(SectionDTO sectiodto) {
  /*      return new Section().MinimumTemprature(sectiodto.getMinimumTemperature())
                .Stock(sectiodto.getStock())
                .StockType(sectiodto.getStockType())
                .WarehouseID(Long.parseLong(sectiodto.getWarehouseID()));
*/ return null;
    }

    public void deletaSection(Long id) {
        try{
            sectionRepository.deleteById(id);
        } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("Referential integrity constraint violation")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }
}
