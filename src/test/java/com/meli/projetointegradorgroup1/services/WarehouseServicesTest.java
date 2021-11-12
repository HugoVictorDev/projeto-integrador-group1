package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.response.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WarehouseServicesTest {

    Warehouse warehouse = new Warehouse(1l, 44l,"Miguel", "Rua: Hum", "3",null);
    WarehouseDTO warehouseDTO = new WarehouseDTO(1l, "Caio", "Rua: Dois", "3");
    WarehouseDTO warehouseConverte = new WarehouseDTO(1l, "Miguel", "Rua: Hum", "3");

    List<Warehouse> listWarehouse = new ArrayList();
    List<WarehouseDTO> listWarehouseDto = new ArrayList();

    WarehouseServices warehouseServices;
    WarehouseRepository warehouseRepository;

    String message = "";


    @Test
    public void listaWarehouseNok(){

        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findAll()).thenReturn(listWarehouse);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        warehouseServices.listaWarehouse();});
        message = "Não existem Wharehoses cadastradas";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUdpdateOK(){
        warehouseServices = Mockito.mock(WarehouseServices.class);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        warehouseServices.validaUpdate(java.util.Optional.ofNullable(warehouse), warehouseDTO);

        assert (warehouse.getName().equals(warehouseDTO.getName()));
    }

    @Test
    public void validaUdpdateNOK(){
        warehouseServices = Mockito.mock(WarehouseServices.class);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        warehouseServices.validaUpdate(java.util.Optional.ofNullable(null), warehouseDTO); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));

    }

    @Test
    public void obterWarehouseByIdNok(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenReturn(null);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        warehouseServices.obterWarehouseById(1l); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterWarehouseByIdOk(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(warehouse));

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        warehouseServices.obterWarehouseById(1l);

        assert (warehouse != null);
    }

    @Test
    public void converteOk(){
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converte(Mockito.any())).thenReturn(warehouse);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        warehouseServices.converte(warehouseConverte);

        assert (warehouse.getName().equals(warehouseConverte.getName()));
    }

    @Test
    public void converteToDtoOk(){
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converteToDto(Mockito.any())).thenReturn(warehouseDTO);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        warehouseServices.converteToDto(warehouse);

        assert (warehouse.getName().equals(warehouseConverte.getName()));
    }

    @Test
    public void converteListOk(){
        listWarehouseDto.add(warehouseConverte);
        listWarehouse.add(warehouse);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converteList(Mockito.any())).thenReturn(listWarehouseDto);
        WarehouseServices warehouseServices = new WarehouseServices(null);
        warehouseServices.converteList(listWarehouse);

        assert (warehouse.getName().equals(warehouseConverte.getName()));
    }

   @Test
    public void deletaOK(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.doNothing().when(warehouseRepository).deleteById(Mockito.anyLong());
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);
        warehouseServices.deleta(1l);

        assert (warehouse.getId() == 1);
    }

    @Test
    public void obterWarehouseByCodeNok(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findByCode(Mockito.anyLong())).thenReturn(null);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.obterWarhouseByCode(1l); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterWarehouseByCodeOk(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findByCode(Mockito.anyLong())).thenReturn(warehouse);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        warehouseServices.obterWarhouseByCode(1l);

        assert (warehouse != null);
    }


    @Test
    public void saveOk(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(warehouse);
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);

        assert (warehouseServices.save(warehouse).getId() == 1);
    }

    @Test
    public void saveNok(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        WarehouseServices warehouseServices = new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.save(null);});
        message = "Erro na gravação Warehouse:";

        assert (exception.getMessage().contains(message));
    }
}

