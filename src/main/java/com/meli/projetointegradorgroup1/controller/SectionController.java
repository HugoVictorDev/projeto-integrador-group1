package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
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

    public SectionController(SectionRepository sectionRepository, SectionServices sectionServices) {
        this.sectionRepository = sectionRepository;
        this.sectionServices = sectionServices;
    }

    //cadastrar sessões
    @PostMapping("/create")
    public SectionDTO ceateSection(@Valid @RequestBody SectionDTO sectionDTO){
           sectionServices.validarWarehouse(sectionDTO);
           Section section = sectionServices.convert(sectionDTO);
           return sectionServices.convertToDto(sectionRepository.save(section));
    }

    //listar todas as sessões
    @GetMapping("/list")
    public Iterable<SectionDTO> list(SectionDTO sectionDTO){
           return sectionServices.convertList(sectionServices.listaSection());
    }

    //busca sessões por id
    @GetMapping("/list/{id}")
    public SectionDTO getSectionByID(@PathVariable ("id") Long id){
           return sectionServices.convertToDto(sectionServices.obterSection(id));
    }

    @PutMapping("/update")
    public SectionDTO updateSection(@Valid @RequestBody SectionDTO sectionDTO){
        Optional<Section> sectionFind = sectionRepository.findById(sectionDTO.getSectionId());
        Section section = sectionServices.validaUpdate(sectionFind, sectionDTO);
        return sectionServices.convertToDto(sectionRepository.save(section));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public SectionDTO  deleteSectionById(@PathVariable("id") Long id) {
           Section section = sectionServices.obterSection(id);
           sectionServices.deletaSection(id);
           return sectionServices.convertToDto(section);
    }
}
