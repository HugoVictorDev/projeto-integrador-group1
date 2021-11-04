package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.services.SectionServices;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Cadastrar nova section")
    public SectionDTO ceateSection(@Valid @RequestBody SectionDTO sectionDTO){
           sectionServices.validarWarehouse(sectionDTO);
           Section section = SectionDTO.converte(sectionDTO);
           return sectionDTO.converte(sectionRepository.save(section));
    }

    //listar todas as sessões
    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de section")
    public Iterable<SectionDTO> list(SectionDTO sectionDTO){
           return sectionDTO.converte(sectionServices.listaSection());
    }

    //busca sessões por id
    @GetMapping("/list/{id}")
    @ApiOperation(value = "Retornar section único a partir do id")
    public SectionDTO getSectionByID(@PathVariable ("id") Long id){
           return SectionDTO.converte(sectionServices.obterSection(id));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar section a partir do id")
    public SectionDTO updateWarehouse(@PathVariable("id") Long id,@Valid @RequestBody SectionDTO sectionDTO){
        Optional<Section> sectionFind = sectionRepository.findById(id);
        Section section = sectionServices.validaUpdate(sectionFind, sectionDTO);
        return SectionDTO.converte(sectionRepository.save(section));
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar section a partir do id")
    public SectionDTO  deleteSectionById(@PathVariable("id") Long id) {
           Section section = sectionServices.obterSection(id);
           sectionRepository.deleteById(id);
           return SectionDTO.converte(section);
    }
}
