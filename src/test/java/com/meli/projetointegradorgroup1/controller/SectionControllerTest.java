package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.services.SectionServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SectionControllerTest {
    Section section = new Section(1l,"1","2","3",1l);
    SectionDTO sectionDTO = new SectionDTO(null,"5","6","7","4");
    Section sectionUpdate = new Section(1l,"5","6","7",1l);

    SectionServices sectionServices;
    SectionRepository sectionRepository;
    List<Section> listSection = new ArrayList();
    List<SectionDTO> listSectionDTO = new ArrayList();


    @Test
    public void createSectionOK(){
        sectionServices = Mockito.mock(SectionServices.class);
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionServices.convert(Mockito.any())).thenReturn(section);
        Mockito.doNothing().when(sectionServices).validarWarehouse(Mockito.any());
        Mockito.when(sectionRepository.save(Mockito.any())).thenReturn(section);

        SectionController sectionController = new SectionController(sectionRepository, sectionServices);
        sectionController.ceateSection(sectionDTO);

        assert (section.getSectionId() != null);
    }
    @Test
    public void listOK(){
        listSection.add(section);

        sectionServices = Mockito.mock(SectionServices.class);
        Mockito.when(sectionServices.listaSection()).thenReturn(listSection);
        Mockito.when(sectionServices.convertList(Mockito.any())).thenReturn(listSectionDTO);

        SectionController sectionController = new SectionController(null, sectionServices);
        sectionController.list(sectionDTO);

        assert (sectionServices.listaSection().size() == 1);
    }
    @Test
    public void getSectionByIDOK(){
        sectionServices = Mockito.mock(SectionServices.class);
        Mockito.when(sectionServices.obterSection(Mockito.anyLong())).thenReturn(section);
        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);

        SectionController sectionController = new SectionController(null, sectionServices);
        sectionController.getSectionByID(1l);

        assert (section.getStockType()!=null);
    }
    @Test
    public void updateSection(){
        sectionServices = Mockito.mock(SectionServices.class);
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(sectionUpdate));
        Mockito.when(sectionServices.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(sectionUpdate);
        Mockito.when(sectionRepository.save(Mockito.any())).thenReturn(sectionUpdate);

        SectionController sectionController = new SectionController(sectionRepository, sectionServices);
        sectionController.updateSection(sectionDTO);

        assert (sectionUpdate.getStockType().equals(sectionDTO.getStockType()));
    }
    @Test
    public void deleteSectionById() {
        sectionServices = Mockito.mock(SectionServices.class);
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionServices.obterSection(Mockito.anyLong())).thenReturn(section);
        Mockito.doNothing().when(sectionRepository).deleteById(Mockito.anyLong());
        SectionController sectionController = new SectionController(sectionRepository, sectionServices);
        sectionController.deleteSectionById(1l);

        assert (section.getSectionId() == 1l);
        }

}
