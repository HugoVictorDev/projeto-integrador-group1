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
import java.util.ArrayList;
import java.util.List;


public class RepresentativeServicesTest {
    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);
    WarehouseServices warehouseServices;

    Representative representative = new Representative(1l, "Joao", "98765432178", warehouse);
    RepresentativeDTO representativedto = new RepresentativeDTO(1l, "Cassio", "98765432178", "4");
    RepresentativeServices representativeServices;
    RepresentativeRepository representativeRepository;
    Representative representativeNull = new Representative();
    List<Representative> representativeList = new ArrayList();

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
    @Test
    public void validaUpdateOK(){
        warehouseServices = Mockito.mock(WarehouseServices.class);
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.obterWarehouse(Mockito.anyLong())).thenReturn(warehouse);
        Mockito.when(representativeServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(representative);

        representativeServices = new RepresentativeServices(warehouseServices);

        representativeServices.validaUpdate(java.util.Optional.ofNullable(representative),representativedto);

        assert (representative.getName().equals(representativedto.getName()));
    }

    @Test
    public void validaUpdateNOK(){
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(null);

        representativeServices = new RepresentativeServices();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        representativeServices.validaUpdate(java.util.Optional.ofNullable(representativeNull),representativedto);});
        message = "Representante não encontrado";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void findRepresentativeOK(){
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.findRepresentative(Mockito.any())).thenReturn(representative);

        representativeServices = new RepresentativeServices();

        representativeServices.findRepresentative(java.util.Optional.ofNullable(representative));

        assert (representative.getName().equals(representative.getName()));
    }

    @Test
    public void findRepresentativeNOK(){
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.findRepresentative(Mockito.any())).thenReturn(representative);

        representativeServices = new RepresentativeServices();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        representativeServices.findRepresentative(java.util.Optional.ofNullable(representativeNull));});
        message = "Representante não encontrado";

        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void listaRepresentativeOK(){
        representativeList.add(representative);

        representativeRepository = Mockito.mock(RepresentativeRepository.class);
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.listaRepresentative()).thenReturn(representativeList);
        Mockito.when(representativeRepository.findAll()).thenReturn(representativeList);

        representativeServices = new RepresentativeServices(representativeRepository);

        assert (representativeList.size() == 1);
    }

    @Test
    public void listaRepresentativeNOK(){

        representativeRepository = Mockito.mock(RepresentativeRepository.class);
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.when(representativeServices.listaRepresentative()).thenReturn(null);
        Mockito.when(representativeRepository.findAll()).thenReturn(representativeList);

        representativeServices = new RepresentativeServices(representativeRepository);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            representativeServices.listaRepresentative();});
        message = "Não existem Representantes cadastradas";

        assert (message.contains(exception.getMessage()));
    }

}
