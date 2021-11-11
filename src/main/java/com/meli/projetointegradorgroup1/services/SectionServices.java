package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
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

    public SectionServices(SectionRepository sectionRepository, WarehouseServices warehouseServices) {
        this.sectionRepository = sectionRepository;
        this.warehouseServices = warehouseServices;
    }

    public void validarWarehouse(SectionRequestDTO sectionDTO) {
       warehouseServices.warehouseExist(sectionDTO.getWarehouseID());
    }

/*        valida warhouse
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
*/

    public List<Section> listaSection() {
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.size() == 0){
            throw new RuntimeException("Não existem Sessões cadastradas");
        }return sectionList;
    }


    public Section obterSection(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (section == null || section.equals(Optional.empty())){
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


    public Section validaUpdate(Optional<Section> sectionFind, SectionRequestDTO sectionDTO) {
        if(sectionFind.isPresent()){
            Section section = sectionFind.get();
            section.setMinimumTemperature(sectionDTO.getMinimumTemperature());
            section.setCapacity(sectionDTO.getCapacity());
            section.setStockType(sectionDTO.getStockType());
            section.setWarehouse(obterWarehouse(sectionDTO.getWarehouseID()));
            return section;
        }else{
            throw new RuntimeException("Section não encontrada");
        }
}

    public Warehouse obterWarehouse(long warehouseID) {
        return warehouseServices.obterWarehouseById(warehouseID);
    }

    public Section convert(SectionRequestDTO dto, WarehouseServices warehouseServices) {
        return Section.builder()
                .code(dto.getCode())
                .stockType(dto.getStockType())
                .minimumTemperature(dto.getMinimumTemperature())
                .capacity(dto.getCapacity())
                .warehouse(warehouseServices.obterWarehouseById(dto.getWarehouseID()))
                .build();
    }

    public SectionDTO convertToDto(Section section) {
        return SectionDTO.builder()
                .code(section.getCode())
                .stockType(section.getStockType())
                .minimumTemperature(section.getMinimumTemperature())
                .capacity(section.getCapacity())
                .warehouseID(section.getWarehouse().getId())
                .build();
    }


    public Iterable<SectionDTO> convertList(List<Section> sections) {
        List<SectionDTO> listaSection = new ArrayList<>();
        for (Section section: sections) {
            listaSection.add( SectionDTO.builder()
                    .code(section.getCode())
                    .stockType(section.getStockType())
                    .minimumTemperature(section.getMinimumTemperature())
                    .capacity(section.getCapacity())
                    .warehouseID(section.getWarehouse().getId())
                    .build());
        }
        return listaSection;
    }

    public Section save(Section section) {
        try {
            sectionRepository.save(section);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação Section:", e );
        }
        return section;
    }

    public void deleta(Long id) {
        try {
            sectionRepository.deleteById(id);
        } catch (RuntimeException e) {
            if (e.getCause().getCause().getMessage().contains("violates foreign key constraint ")) {
                throw new RuntimeException("violates foreign key constraint");
            } else {
                throw e;
            }
        }
    }


    public Optional<Section> findById(Long id) {
        return null;
    }
}
