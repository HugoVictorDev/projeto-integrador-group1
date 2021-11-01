package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WarehouseServicesTest {

    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);
    WarehouseServices warehouseServices;
    WarehouseRepository warehouseRepository;
    String message = "";

    @Test
    public void valida(){
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        Mockito.when(warehouseRepository.findBywarehouseId(Mockito.anyLong())).thenReturn(null);
        WarehouseServices warehouseServices= new WarehouseServices(warehouseRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            warehouseServices.valida(1l);});
        message = "Warehouse não cadastrada";
        assert (message.contains(exception.getMessage()));}
}
