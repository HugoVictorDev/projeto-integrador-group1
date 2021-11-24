package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class WarehouseServicesTest {

    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",null);
    WarehouseDTO warehouseDTO = new WarehouseDTO(1l, "Caio", "Rua: Dois", "3");
    WarehouseDTO warehouseConverte = new WarehouseDTO(1l, "Miguel", "Rua: Hum", "3");

    List<Warehouse> listWarehouse = new ArrayList();
    List<WarehouseDTO> listWarehouseDto = new ArrayList();

    WarehouseServices warehouseServices = Mockito.mock(WarehouseServices.class);
    WarehouseRepository warehouseRepository = Mockito.mock(WarehouseRepository.class);

    String message = "";
    String uri = "http//Mock";

    @Test
    public void listaWarehouseNok(){
        Mockito.when(warehouseRepository.findAll()).thenReturn(listWarehouse);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.listaWarehouse();});
        message = "Não existem Wharehoses cadastradas";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUdpdateOK(){
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        warehouseServices.validaUpdate(java.util.Optional.ofNullable(warehouse), warehouseDTO);
        assert (warehouse.getName().equals(warehouseDTO.getName()));
    }

    @Test
    public void validaUdpdateNOK(){
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.validaUpdate(java.util.Optional.ofNullable(null), warehouseDTO); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));

    }

    @Test
    public void obterWarehouseByIdNok(){
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenReturn(null);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.obterWarehouseById(1l); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterWarehouseByIdOk(){
        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(warehouse));
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        assert (warehouseServices.obterWarehouseById(1l).getId() == 1);
    }

    @Test
    public void converteOk(){
        Mockito.when(warehouseServices.converte(Mockito.any())).thenReturn(warehouse);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        assert (warehouseServices.converte(warehouseConverte).getName().equals(warehouseConverte.getName()));
    }

    @Test
    public void converteToDtoOk(){
        Mockito.when(warehouseServices.convertToDto(Mockito.any())).thenReturn(warehouseDTO);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        assert (warehouseServices.convertToDto(warehouse).getName().equals(warehouseConverte.getName()));
    }

    @Test
    public void converteListOk(){
        listWarehouseDto.add(warehouseConverte);
        listWarehouse.add(warehouse);
        Mockito.when(warehouseServices.converteList(Mockito.any())).thenReturn(listWarehouseDto);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        warehouseServices.converteList(listWarehouse);
        assert (warehouse.getName().equals(warehouseConverte.getName()));
    }

   @Test
    public void deletaOK(){
        Mockito.doNothing().when(warehouseRepository).deleteById(Mockito.anyLong());
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);
        warehouseServices.deleta(1l);
        assert (warehouse.getId() == 1);
    }

    @Test
    public void obterWarehouseByCodeNok(){
        Mockito.when(warehouseRepository.findByCode(Mockito.anyLong())).thenReturn(null);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.obterWarhouseByCode(1l); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterWarehouseByCodeOk(){
        Mockito.when(warehouseRepository.findByCode(Mockito.anyLong())).thenReturn(warehouse);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        assert (warehouseServices.obterWarhouseByCode(44l).getCode() == 44);
    }

    @Test
    public void saveOk(){
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(warehouse);
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);
        assert (warehouseServices.save(warehouse, uriBuilder).getStatusCodeValue() == 201 );
    }

    @Test
    public void saveNok(){
        Mockito.when(warehouseRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);
        assert (warehouseServices.save(warehouse, null).getStatusCodeValue() == 400 );
    }
}

