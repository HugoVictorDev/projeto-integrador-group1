package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WarehouseControllerTest {
    /*
    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);
    WarehouseDTO warehouseDTO = new WarehouseDTO(null, "Miguel", "Rua: Hum", "3");
    WarehouseDTO warehouseUpdate = new WarehouseDTO(4l, "Miguel", "Rua: Hum", "3");
    List<Warehouse> listWarehouse = new ArrayList();
    List<WarehouseDTO> listWarehouseDto = new ArrayList();
    WarehouseServices warehouseServices;
    WarehouseRepository warehouseRepository;

    @Test
    public void createWarehouseOK() {
        warehouseRepository = Mockito.mock(WarehouseRepository.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(warehouse);
        Mockito.when(warehouseServices.converte(Mockito.any())).thenReturn(warehouse);
        Mockito.when(warehouseServices.converteToDto(Mockito.any())).thenReturn(warehouseDTO);

        WarehouseController warehouseController = new WarehouseController(warehouseRepository, warehouseServices);
        warehouseController.createWarehouse(warehouseDTO);

        assert(warehouse.getId()!=null);
    }

    @Test
    public void listOK() {
        listWarehouse.add(warehouse);

        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converteList(Mockito.anyList())).thenReturn(listWarehouseDto);
        Mockito.when(warehouseServices.listaWarehouse()).thenReturn(listWarehouse);
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.list(warehouseDTO);

        assert(listWarehouse.size() == 1);
    }

    @Test
    public void getWarehouseByIdOK() {
        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converteToDto(Mockito.any())).thenReturn(warehouseDTO);
        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.getWarehouseById(1l);

        assert(warehouse.getName()!=null);
    }

    @Test
    public void UpdateWarehouseOk() {
        warehouseServices = Mockito.mock(WarehouseServices.class);
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseServices.converteToDto(Mockito.any())).thenReturn(warehouseDTO);
        Mockito.when(warehouseRepository.findBywarehouseId(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.when(warehouseServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(warehouse);
        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(warehouse);
        WarehouseController warehouseController = new WarehouseController(warehouseRepository,warehouseServices);
        warehouseController.updateWarehouse(warehouseUpdate);

        assert(warehouse.getName().equals(warehouseUpdate.getName()));
    }

    @Test
    public void deleteWarehouseByIdOk() {

        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.converteToDto(Mockito.any())).thenReturn(warehouseDTO);
        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.doNothing().when(warehouseServices).deleta(Mockito.anyLong());
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.deleteWarehouseById(4l);

        assert(warehouse.getWarehouseId() == 4);
    }*/
}
