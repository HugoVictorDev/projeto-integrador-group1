package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WarehouseControllerTest {
    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);
    WarehouseDTO warehouseDTO = new WarehouseDTO(null, "Miguel", "Rua: Hum", "3");
    WarehouseDTO warehouseUpdate = new WarehouseDTO(4l, "Miguel", "Rua: Hum", "3");
    List<Warehouse> listWarehouse = new ArrayList();
    WarehouseServices warehouseServices;
    WarehouseRepository warehouseRepository;

    @Test
    public void createWarehouseOK() {

        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(warehouse);

        WarehouseController warehouseController = new WarehouseController(warehouseRepository, null);
        warehouseController.createWarehouse(warehouseDTO);

        assert(warehouse.getWarehouseId()!=null);
    }

    @Test
    public void ListaWarehouseOk() {
        listWarehouse.add(warehouse);

        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.listaWarehouse()).thenReturn(listWarehouse);
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.list(warehouseDTO);

        assert(listWarehouse.size() == 1);
    }

    @Test
    public void ListaIdWarehouseOk() {

        warehouseServices = Mockito.mock(WarehouseServices.class);

        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.getWarehouseById(1l);

        assert(warehouse.getName()!=null);
    }

    @Test
    public void UpdateWarehouseOk() {

        warehouseServices = Mockito.mock(WarehouseServices.class);
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

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

        Mockito.when(warehouseServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.doNothing().when(warehouseServices).deleta(Mockito.anyLong());
        WarehouseController warehouseController = new WarehouseController(null,warehouseServices);
        warehouseController.deleteWarehouseById(4l);

        assert(warehouse.getWarehouseId() == 4);
    }
}
