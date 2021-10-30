package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.dto.request.SectionDTOHugo;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.services.SectionServices;
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

    //cadastrar sessões
    @PostMapping("/create")
    public SectionDTOHugo ceateSection(@Valid @RequestBody SectionDTOHugo sectionDTOHugo){
           sectionServices.validWarhouseExist(sectionDTOHugo);
//           sectionServices.validSectionExist(sectionDTOHugo);
          this.sectionRepository.save(sectionDTOHugo.build());
          return sectionDTOHugo;
    }

    //listar todas as sessões
    @GetMapping("/list")
    public Iterable<SectionDTO> list(SectionDTO sectionDTO){
           return sectionDTO.converte(sectionServices.listaSection());
    }

    //busca sessões por id
    @GetMapping("/list/{id}")
    public SectionDTO getSectionByID(@PathVariable ("id") Long id){
           return SectionDTO.converte(sectionServices.obterSection(id));
    }

    @PutMapping("/update/{id}")
    public SectionDTO updateWarehouse(@PathVariable("id") Long id,@Valid @RequestBody SectionDTO sectionDTO){
        Optional<Section> sectionFind = sectionRepository.findById(id);
        Section section = sectionServices.validaUpdate(sectionFind, sectionDTO);
        return SectionDTO.converte(sectionRepository.save(section));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public SectionDTO  deleteSectionById(@PathVariable("id") Long id) {
           Section section = sectionServices.obterSection(id);
           sectionRepository.deleteById(id);
           return SectionDTO.converte(section);
    }
}
