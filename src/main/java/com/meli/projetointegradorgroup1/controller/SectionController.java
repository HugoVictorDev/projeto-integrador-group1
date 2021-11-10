package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.services.SectionServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/section")
public class SectionController {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SectionServices sectionServices;

    @Autowired
    private WarehouseServices warehouseServices;

    @PostMapping("/create")
    public SectionDTO ceateSection(@Valid @RequestBody SectionDTO sectionDTO){
        sectionServices.validarWarehouse(sectionDTO);
         this.sectionRepository.save(sectionDTO.converteBuilder(sectionDTO, warehouseServices)) ;
         return sectionDTO;
    }


    //listar todas as sessões
    @GetMapping("/list")
    public Iterable<SectionDTO> list(SectionDTO sectionDTO){
//           return sectionDTO.converte(sectionServices.listaSection()); TODO REVISAR
        return null;
    }

    //busca sessões por id
    @GetMapping("/list/{id}")
    public SectionDTO getSectionByID(@PathVariable ("id") Long id){
//           return SectionDTO.converte(sectionServices.obterSection(id)); REVISAR TODO
        return null;
    }

    @PutMapping("/update/{id}")
    public SectionDTO updateWarehouse(@PathVariable("id") Long id,@Valid @RequestBody SectionDTO sectionDTO){
        Optional<Section> sectionFind = sectionRepository.findById(id);
        Section section = sectionServices.validaUpdate(sectionFind, sectionDTO);
//        return SectionDTO.converte(sectionRepository.save(section)); TODO REVISAR
        return null;
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public SectionDTO  deleteSectionById(@PathVariable("id") Long id) {
           Section section = sectionServices.obterSection(id);
           sectionRepository.deleteById(id);
//           return SectionDTO.converte(section); TODO REVISAR
        return null;
    }
}
