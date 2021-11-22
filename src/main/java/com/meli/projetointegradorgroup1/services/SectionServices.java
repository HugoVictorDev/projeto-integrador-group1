package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionForInboundDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionResponseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
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

    public void validarWarehouse(SectionRequestDTO sectionRequestDTO) {
       warehouseServices.warehouseExist(sectionRequestDTO.getWarehouseID());
    }


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

    public StockType obtemTypeStockSection(Long code) {
        Section section = sectionRepository.findByCode(code);
        StockType stockType = section.getStockType();
        return stockType;

    }

    public int obtemQuantidadeDoSection(Long code){
        Section section = sectionRepository.findByCode(code);
        Long capacity = section.getCapacity();
        return capacity.intValue();

    }

    public Section validaUpdate(Optional<Section> sectionFind, SectionRequestDTO sectionRequestDTO) {
        if(sectionFind.isPresent()){
            Section section = sectionFind.get();
            section.setMinimumTemperature(sectionRequestDTO.getMinimumTemperature());
            section.setCapacity(sectionRequestDTO.getCapacity());
            section.setStockType(sectionRequestDTO.getStockType());
            section.setWarehouse(obterWarehouse(sectionRequestDTO.getWarehouseID()));
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

    public SectionResponseDTO convertToDto(Section section) {
        return SectionResponseDTO.builder()
                .code(section.getCode())
                .stockType(section.getStockType())
                .minimumTemperature(section.getMinimumTemperature())
                .capacity(section.getCapacity())
                .warehouseID(section.getWarehouse().getId())
                .build();
    }


    public Iterable<SectionResponseDTO> convertList(List<Section> sections) {
        List<SectionResponseDTO> listaSection = new ArrayList();
        for (Section section: sections) {
            listaSection.add( SectionResponseDTO.builder()
                    .code(section.getCode())
                    .stockType(section.getStockType())
                    .minimumTemperature(section.getMinimumTemperature())
                    .capacity(section.getCapacity())
                    .warehouseID(section.getWarehouse().getId())
                    .build());
        }
        return listaSection;
    }

    public ResponseEntity<Object>save(Section section, UriComponentsBuilder uriBuilder) {
        try {
            sectionRepository.save(section);
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RuntimeException("Erro na gravação Section:", e));
        }
        URI uri = uriBuilder.path("/warehouse/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity
                .created(uri).body(convertToDto(section));
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

}
