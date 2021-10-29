package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.dto.WarehouseDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class RepresentativeServicesTest {
    WarehouseDTO warehousedto = new WarehouseDTO(4l, "Marco", "Rua: Hum", "3");
    Warehouse warehouse = new Warehouse(4l, "Marco", "Rua: Hum", "3",null);
    WarehouseServices warehouseServices;

    Representative representative = new Representative(1l, "Mario", "78945678945", warehouse);
    RepresentativeDTO representativedto = new RepresentativeDTO(1l, "Mario", "78945678945", "4");
    RepresentativeServices representativeServices;
    RepresentativeRepository representativeRepository;

    String message = "null";

    @Test
    public void obterWarehouseOK(){
        warehouseServices = Mockito.mock(WarehouseServices.class);
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);

        representativeServices = new RepresentativeServices(warehouseServices);
        representativeServices.obterWarehouse(4l);
        assert (warehouse.getWarehouseId().equals(warehouse.getWarehouseId()));
    }


    @Test
    public void valida(){
        representativeServices = Mockito.mock(RepresentativeServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.doNothing().when(representativeServices).valida(Mockito.any());
        Mockito.doNothing().when(representativeServices).validarWarehouse(Mockito.anyLong());
        Mockito.when(representativeRepository.findAllByCpfAndWarehouse_WarehouseId(Mockito.anyString(),Mockito.anyLong())).thenReturn(null);

        representativeServices = new RepresentativeServices(warehouseServices, representativeRepository);
        representativeServices.valida(representativedto);

        Assert.assertEquals(representativedto.getCpf(), representative.getCpf());
    }

    @Test
    public void validaCpfNok(){
        representativeServices = Mockito.mock(RepresentativeServices.class);
        warehouseServices = Mockito.mock(WarehouseServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.doNothing().when(representativeServices).valida(Mockito.any());
        Mockito.doNothing().when(representativeServices).validarWarehouse(Mockito.anyLong());
        Mockito.when(representativeRepository.findAllByCpfAndWarehouse_WarehouseId(Mockito.anyString(),Mockito.anyLong())).thenReturn(representative);

        representativeServices = new RepresentativeServices(warehouseServices, representativeRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{ representativeServices.valida(representativedto);});
        message = "CPF já cadstrado para essa Warehouse";
        assert (message.contains(exception.getMessage()));
    }

}
