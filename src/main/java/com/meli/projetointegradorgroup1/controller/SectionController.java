package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionResponseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.services.SectionServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/section")
public class SectionController {

    @Autowired
    private SectionServices sectionServices;

    @Autowired
    private WarehouseServices warehouseServices;

    public SectionController(SectionServices sectionServices) {
        this.sectionServices = sectionServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> ceateSection(@Valid @RequestBody SectionRequestDTO sectionRequestDTO, UriComponentsBuilder uriBuilder){
        sectionServices.validarWarehouse(sectionRequestDTO);
        Section section = sectionServices.convert(sectionRequestDTO, warehouseServices);
        return sectionServices.save(section, uriBuilder);
    }

    //listar todas as sessões
    @GetMapping("/list")
    public Iterable<SectionResponseDTO> list(){
        return sectionServices.convertList(sectionServices.listaSection());
    }

    //busca sessões por id
    @GetMapping("/list/{id}")
    public SectionResponseDTO getSectionByID(@PathVariable ("id") Long id){
        return sectionServices.convertToDto(sectionServices.obterSection(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSection(@PathVariable("id") Long id, @Valid @RequestBody SectionRequestDTO sectionRequestDTO, UriComponentsBuilder uriBuilder){
        Section sectionFind = sectionServices.obterSection(id);
        Section section = sectionServices.validaUpdate(Optional.ofNullable(sectionFind), sectionRequestDTO);
        return sectionServices.save(section, uriBuilder);
    }

    //deletar por id
    @DeleteMapping("/delete/{id}")
    public SectionResponseDTO deleteSectionById(@PathVariable("id") Long id) {
        Section section = sectionServices.obterSection(id);
        sectionServices.deleta(id);
        return sectionServices.convertToDto(section);
    }
}
