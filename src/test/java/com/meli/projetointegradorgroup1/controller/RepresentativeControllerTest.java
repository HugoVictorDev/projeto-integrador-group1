package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import com.meli.projetointegradorgroup1.services.WarehouseServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RepresentativeControllerTest {
    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);
    WarehouseServices warehouseServices;

    Representative representative = new Representative(1l, "Joao", "98765432178", warehouse);
    RepresentativeDTO representativedto = new RepresentativeDTO(null, "Cassio", "98765432178", "4");
    RepresentativeServices representativeServices;
    RepresentativeRepository representativeRepository;
    Representative representativeNull = new Representative();
    List<Representative> representativeList = new ArrayList();

    @Test
    public void createRepresentative(){
        representativeServices = Mockito.mock(RepresentativeServices.class);

        Mockito.doNothing().when(representativeServices).valida(Mockito.any());
        Mockito.when(RepresentativeDTO.converte(Mockito.any(RepresentativeDTO.class))).thenReturn(representative);
        Mockito.when(RepresentativeDTO.converteDto(Mockito.any(Representative.class))).thenReturn(representativedto);

        RepresantiveController represantiveController = new RepresantiveController(representativeServices);
        represantiveController.createRepresentative(representativedto);

        assert (representativedto.getRepresentative_Id().equals(representative.getRepresentative_Id()));
    }
    @Test
    public void getRepresentativeList(){

    }
    @Test
    public void updateRepresentative(){

    }
    @Test
    public void getRepresentativeById(){

    }
    @Test
    public void deleteRepresentativeById() {
    }
}
