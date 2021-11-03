package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WarehouseServicesTest {

    Warehouse warehouse = new Warehouse(1l, "Miguel", "Rua: Hum", "3",null);
    WarehouseDTO warehouseDTO = new WarehouseDTO(1l, "Caio", "Rua: Dois", "3");
    WarehouseServices warehouseServices;
    WarehouseRepository warehouseRepository;
    List<Warehouse> listWarehouse = new ArrayList();
    String message = "";

    @Test
    public void validaWarehouseNok(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findBywarehouseId(Mockito.anyLong())).thenReturn(null);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        warehouseServices.valida(1l);});
        message = "Warehouse não cadastrada";
        assert (message.contains(exception.getMessage()));
}
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
    public void obterWarehouseNok(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findBywarehouseId(Mockito.anyLong())).thenReturn(null);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        warehouseServices.obterWarehouse(1l); });
        message = "Warehouse não encontrada";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterWarehouseOk(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findBywarehouseId(Mockito.anyLong())).thenReturn(warehouse);

        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);
        warehouseServices.obterWarehouse(1l);

        assert (warehouse != null);
    }
}
