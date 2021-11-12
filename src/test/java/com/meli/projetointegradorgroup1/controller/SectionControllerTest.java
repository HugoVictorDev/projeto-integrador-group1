package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.services.SectionServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SectionControllerTest {

    Section section = new Section(1l, 2l,StockType.FRESH,"30", 8l,null);
    SectionRequestDTO sectionRequestDTO = new SectionRequestDTO (null,1l,"5", StockType.FRESH,7l,1l);
    SectionDTO sectionDTO = new SectionDTO(2l,"30",StockType.FRESH, 8l,null);
    Section sectionUpdate = new Section(1l, 2l,StockType.FRESH,"30", 8l,null);


    SectionServices sectionServices;
    List<Section> listSection = new ArrayList();
    List<SectionDTO> listSectionDTO = new ArrayList();

    @Test
    public void createSectionOK(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionServices.convert(Mockito.any(), Mockito.any())).thenReturn(section);
        Mockito.doNothing().when(sectionServices).validarWarehouse(Mockito.any());

        SectionController sectionController = new SectionController(sectionServices);
        sectionController.ceateSection(sectionRequestDTO);

        assert (section.getId() != null);
    }
    @Test
    public void listOK(){
        listSection.add(section);

        sectionServices = Mockito.mock(SectionServices.class);
        Mockito.when(sectionServices.listaSection()).thenReturn(listSection);
        Mockito.when(sectionServices.convertList(Mockito.any())).thenReturn(listSectionDTO);

        SectionController sectionController = new SectionController(sectionServices);
        sectionController.list();

        assert (sectionServices.listaSection().size() == 1);
    }
    @Test
    public void getSectionByIDOK(){
        sectionServices = Mockito.mock(SectionServices.class);
        Mockito.when(sectionServices.obterSection(Mockito.anyLong())).thenReturn(section);
        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);

        SectionController sectionController = new SectionController( sectionServices);
        sectionController.getSectionByID(1l);

        assert (section.getStockType()!=null);
    }
    @Test
    public void updateSection(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionServices.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(sectionUpdate));
        Mockito.when(sectionServices.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(sectionUpdate);
        Mockito.when(sectionServices.save(Mockito.any())).thenReturn(sectionUpdate);

        SectionController sectionController = new SectionController(sectionServices);
        sectionController.updateSection(1l, sectionRequestDTO);

        assert (sectionUpdate.getStockType().equals(sectionDTO.getStockType()));
    }
    @Test
    public void deleteSectionById() {
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        Mockito.when(sectionServices.obterSection(Mockito.anyLong())).thenReturn(section);
        Mockito.doNothing().when(sectionServices).deleta(Mockito.anyLong());
        SectionController sectionController = new SectionController(sectionServices);
        sectionController.deleteSectionById(1l);

        assert (section.getId() == 1l);
        }

}
