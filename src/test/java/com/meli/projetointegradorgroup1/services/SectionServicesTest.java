package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionResponseDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class SectionServicesTest {

    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",null);

    Section section = new Section(1l, 2l, StockType.FRESH,"30", 8l, warehouse);
    SectionRequestDTO sectionRequestDTO = new SectionRequestDTO(null, 1L,"5", StockType.FRESH,7l, 1L);
    SectionRequestDTO sectionUpdate = new SectionRequestDTO(null,1l,"5", StockType.FRESH,7l,1l);
    SectionResponseDTO sectionResponseDTO = new  SectionResponseDTO( 2l, "30", StockType.FRESH, 8l,null);

    WarehouseServices warehouseServices = Mockito.mock(WarehouseServices.class);
    SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
    SectionServices sectionServices = Mockito.mock(SectionServices.class);
    List<Section> listSection = new ArrayList();
    List<SectionResponseDTO> listSectionRequestDTO = new ArrayList();

    String message = "";
    String uri = "http//Mock";


    @Test
    public void listaSectionOk(){
        listSection.add(section);
        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);
        sectionServices = new SectionServices(sectionRepository, warehouseServices);
        assert (sectionServices.listaSection().size() == 1);
    }

    @Test
    public void listaSectionNok(){
        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);
        sectionServices = new SectionServices(sectionRepository, warehouseServices);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.listaSection();});
        message = "Não existem Sessões cadastradas";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterSectionOk(){
        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(section));
        sectionServices = new SectionServices(sectionRepository, warehouseServices);
        assert (sectionServices.obterSection(1l).getId().equals(1l));
    }

    @Test
    public void obterSectionNok(){
        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(null);
        sectionServices = new SectionServices( sectionRepository, warehouseServices);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.obterSection(1l);});
        message = "Section não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);
        Mockito.when(sectionServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        sectionServices = new SectionServices(null,warehouseServices);
        sectionServices.validaUpdate(java.util.Optional.ofNullable(section), sectionUpdate);
        assert (section.getStockType().equals(sectionRequestDTO.getStockType()));
    }

    @Test
    public void validaUpdateNok(){
        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);
        sectionServices = new SectionServices(null,null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.validaUpdate(java.util.Optional.ofNullable(null),sectionUpdate);});
        message = "Section não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void converteOk(){
        Mockito.when(sectionServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.when(sectionServices.convert(Mockito.any(), Mockito.any())).thenReturn(section);
        SectionServices sectionServices = new SectionServices(null,warehouseServices);
        sectionServices.convert(sectionRequestDTO, warehouseServices);
        assert (section.getStockType().equals(sectionRequestDTO.getStockType()));
    }

    @Test
    public void converteToDtoOk(){
        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionResponseDTO);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertToDto(section);
        assert (section.getStockType().equals(sectionRequestDTO.getStockType()));
    }

    @Test
    public void converteListOk(){
        listSectionRequestDTO.add(sectionResponseDTO);
        listSection.add(section);
        Mockito.when(sectionServices.convertList(Mockito.any())).thenReturn(listSectionRequestDTO);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertList(listSection);
        assert (section.getStockType().equals(sectionRequestDTO.getStockType()));
    }

    @Test
    public void deletaSectioOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);
        Mockito.doNothing().when(sectionRepository).deleteById(Mockito.anyLong());
        SectionServices sectionServices = new SectionServices(sectionRepository, null );
        sectionServices.deleta(1l);
        assert (section.getId() == 1);
    }

    @Test
    public void validarWarehouseOk(){
        Mockito.when(warehouseServices.warehouseExist(Mockito.anyLong())).thenReturn(true);
        SectionServices sectionServices = new SectionServices(null , warehouseServices );
        sectionServices.validarWarehouse(sectionRequestDTO);
        assert (sectionRequestDTO.getWarehouseID() == 1);
    }

    @Test
    public void obterSectionByCodeOk(){
        Mockito.when(sectionRepository.findByCode(Mockito.anyLong())).thenReturn(section);
        sectionServices = new SectionServices(sectionRepository, null);
        assert (sectionServices.obterSectionByCode(2l).getCode() == 2);
    }

    @Test
    public void obterSectionByCodeNok(){
        Mockito.when(sectionRepository.findByCode(Mockito.anyLong())).thenReturn(null);
        sectionServices = new SectionServices( sectionRepository, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.obterSectionByCode(1l);});
        message = "Section não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void saveOk(){
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(sectionRepository.save(Mockito.any())).thenReturn(section);
        sectionServices = new SectionServices(sectionRepository, null);
        assert (sectionServices.save(section, uriBuilder).getStatusCodeValue() == 201 );
    }

    @Test
    public void saveNok(){
        Mockito.when(sectionRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        sectionServices = new SectionServices(sectionRepository, null);
        assert (sectionServices.save(section, null).getStatusCodeValue() == 400 );
    }

}
