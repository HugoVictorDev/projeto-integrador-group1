package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SectionServicesTest {
    Section section = new Section(1l,"1","2","3",null);
    SectionDTO sectionDTO = new SectionDTO(null,"5","6","7","4");
    SectionServices sectionServices;
    SectionRepository sectionRepository;
    List<Section> listSection = new ArrayList();

    WarehouseServices warehouseServices;
    Warehouse warehouse = new Warehouse(1l, "Miguel", "Rua: Hum", "3",null);

    String message = "";

    @Test
    public void validarWarehouseOk(){
        warehouseServices = Mockito.mock(WarehouseServices.class);
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.doNothing().when(warehouseServices).valida(Mockito.anyLong());

        sectionServices = new SectionServices(warehouseServices, null);
        sectionServices.validarWarehouse(sectionDTO);

        Mockito.verify(warehouseServices, Mockito.times(1)).valida(4);
    }

    @Test
    public void listaSectionOk(){
        listSection.add(section);

        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(null, sectionRepository);
        sectionServices.listaSection();

        assert (listSection.size() == 1);
    }

    @Test
    public void listaSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(null, sectionRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.listaSection();});
        message = "Não existem Sessões cadastradas";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterSectionOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findBysectionId(Mockito.anyLong())).thenReturn(section);

        sectionServices = new SectionServices(null, sectionRepository);
        sectionServices.obterSection(1l);

        assert (section.getSectionId().equals(1l));
    }

    @Test
    public void obterSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findBysectionId(Mockito.anyLong())).thenReturn(null);

        sectionServices = new SectionServices(null, sectionRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.obterSection(1l);});
        message = "Sessão não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        sectionServices = Mockito.mock(SectionServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);
        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);

        sectionServices = new SectionServices(warehouseServices,null);
        sectionServices.validaUpdate(java.util.Optional.ofNullable(section),sectionDTO);

        assert (section.getStockType().equals(sectionDTO.getStockType()));
    }

    @Test
    public void validaUpdateNok(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);

        sectionServices = new SectionServices(null,null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.validaUpdate(java.util.Optional.ofNullable(null),sectionDTO);});
        message = "Sessão não encontrada";

        assert (message.contains(exception.getMessage()));
    }


}
