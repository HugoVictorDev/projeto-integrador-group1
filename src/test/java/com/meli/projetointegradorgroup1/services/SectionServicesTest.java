package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SectionRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SectionDTO;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SectionRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SectionServicesTest {

    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",null);

    Section section = new Section(1l, 2l, StockType.FRESH,"30", 8l, warehouse);
    SectionRequestDTO sectionRequestDTO = new SectionRequestDTO (null, 1L,"5", StockType.FRESH,7l, 1L);
    SectionDTO sectionDTO = new SectionDTO(2l,"30",StockType.FRESH, 8l,null);
    SectionRequestDTO sectionUpdate = new SectionRequestDTO (null,1l,"5", StockType.FRESH,7l,1l);

    Section sectionNull = new Section();

    WarehouseServices warehouseServices;
    SectionRepository sectionRepository;
    SectionServices sectionServices;
    List<Section> listSection = new ArrayList();
    List<SectionDTO> listSectionDTO = new ArrayList();


    String message = "";


    @Test
    public void listaSectionOk(){
        listSection.add(section);

        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(sectionRepository, warehouseServices);
        sectionServices.listaSection();

        assert (listSection.size() == 1);
    }

    @Test
    public void listaSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findAll()).thenReturn(listSection);

        sectionServices = new SectionServices(sectionRepository, warehouseServices);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.listaSection();});
        message = "Não existem Sessões cadastradas";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterSectionOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(section));

        sectionServices = new SectionServices(sectionRepository, warehouseServices);
        sectionServices.obterSection(1l);

        assert (section.getId().equals(1l));
    }

    @Test
    public void obterSectionNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findById(Mockito.anyLong())).thenReturn(null);

        sectionServices = new SectionServices( sectionRepository, warehouseServices);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.obterSection(1l);});
        message = "Section não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        sectionServices = Mockito.mock(SectionServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);
        Mockito.when(sectionServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);

        sectionServices = new SectionServices(null,warehouseServices);
        sectionServices.validaUpdate(java.util.Optional.ofNullable(section), sectionUpdate);

        assert (section.getStockType().equals(sectionDTO.getStockType()));
    }

    @Test
    public void validaUpdateNok(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(section);

        sectionServices = new SectionServices(null,null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sectionServices.validaUpdate(java.util.Optional.ofNullable(null),sectionUpdate);});
        message = "Section não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void converteOk(){
        sectionServices = Mockito.mock(SectionServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(sectionServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.when(sectionServices.convert(Mockito.any(), Mockito.any())).thenReturn(section);

        SectionServices sectionServices = new SectionServices(null,warehouseServices);
        sectionServices.convert(sectionRequestDTO, warehouseServices);

        assert (section.getStockType().equals(sectionRequestDTO.getStockType()));
    }

    @Test
    public void converteToDtoOk(){
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertToDto(Mockito.any())).thenReturn(sectionDTO);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertToDto(section);

        assert (section.getStockType().equals(sectionDTO.getStockType()));
    }

    @Test
    public void converteListOk(){
        listSectionDTO.add(sectionDTO);
        listSection.add(section);
        sectionServices = Mockito.mock(SectionServices.class);

        Mockito.when(sectionServices.convertList(Mockito.any())).thenReturn(listSectionDTO);
        SectionServices sectionServices = new SectionServices(null,null);
        sectionServices.convertList(listSection);

        assert (section.getStockType().equals(sectionDTO.getStockType()));
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
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.warehouseExist(Mockito.anyLong())).thenReturn(true);
        SectionServices sectionServices = new SectionServices(null , warehouseServices );
        sectionServices.validarWarehouse(sectionRequestDTO);

        assert (sectionRequestDTO.getWarehouseID() == 1);
    }

    @Test
    public void obterSectionByCodeOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findByCode(Mockito.anyLong())).thenReturn(section);

        sectionServices = new SectionServices(sectionRepository, null);
        sectionServices.obterSectionByCode(2l);

        assert(section.getCode() == 2);
    }

    @Test
    public void obterSectionByCodeNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.findByCode(Mockito.anyLong())).thenReturn(null);

        sectionServices = new SectionServices( sectionRepository, null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.obterSectionByCode(1l);});
        message = "Section não encontrada";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void saveOk(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.save(Mockito.any())).thenReturn(section);
        sectionServices = new SectionServices(sectionRepository, null);

        assert (sectionServices.save(section).getId() == 1);
    }

    @Test
    public void saveNok(){
        sectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(sectionRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        sectionServices = new SectionServices(sectionRepository, null);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sectionServices.save(null);});
        message = "Erro na gravação Section:";

        assert (exception.getMessage().contains(message));
    }
}
