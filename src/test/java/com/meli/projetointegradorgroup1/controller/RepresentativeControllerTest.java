package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.RepresentativeServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RepresentativeControllerTest {
    Warehouse warehouse = new Warehouse(4l, "Miguel", "Rua: Hum", "3",null);

    Representative representative = new Representative(1l, "Joao", "98765432178", warehouse);
    Representative representativeUpdate = new Representative(1l, "Cassio", "98765432178", warehouse);
    RepresentativeDTO representativedto = new RepresentativeDTO(null, "Cassio", "98765432178", "4");
    RepresentativeServices representativeServices;
    RepresentativeRepository representativeRepository;
    List<Representative> representativeList = new ArrayList();

    @Test
    public void createRepresentative(){

        representativeServices = Mockito.mock(RepresentativeServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.doNothing().when(representativeServices).valida(Mockito.any());
        Mockito.when(representativeRepository.save(Mockito.any())).thenReturn(representative);

        RepresantiveController represantiveController = new RepresantiveController(representativeServices, representativeRepository);
        represantiveController.createRepresentative(representativedto);

        assert (representative.getRepresentative_Id() != null);
    }
    @Test
    public void getRepresentativeList(){
        representativeList.add(representative);

        representativeServices = Mockito.mock(RepresentativeServices.class);
        Mockito.when(representativeServices.listaRepresentative()).thenReturn(representativeList);

        RepresantiveController represantiveController = new RepresantiveController(representativeServices, representativeRepository);
        represantiveController.getRepresentativeList();

        assert (representativeServices.listaRepresentative().size() == 1);
    }
    @Test
    public void updateRepresentative(){
        representativeServices = Mockito.mock(RepresentativeServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.when(representativeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(representativeUpdate));
        Mockito.when(representativeServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(representativeUpdate);
        Mockito.when(representativeRepository.save(Mockito.any())).thenReturn(representativeUpdate);

        RepresantiveController represantiveController = new RepresantiveController(representativeServices, representativeRepository);
        represantiveController.updateRepresentative(representativedto);

        assert (representativeUpdate.getName().equals(representativedto.getName()));
    }
    @Test
    public void getRepresentativeById(){
        representativeServices = Mockito.mock(RepresentativeServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.when(representativeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(representative));
        Mockito.when(representativeServices.findRepresentative(Mockito.any())).thenReturn(representative);

        RepresantiveController represantiveController = new RepresantiveController(representativeServices, representativeRepository);
        represantiveController.getRepresentativeById(1l);

        assert (representative.getName()!=null);
    }
    @Test
    public void deleteRepresentativeById() {
        representativeServices = Mockito.mock(RepresentativeServices.class);
        representativeRepository = Mockito.mock(RepresentativeRepository.class);

        Mockito.when(representativeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(representative));
        Mockito.when(representativeServices.findRepresentative(Mockito.any())).thenReturn(representative);
        Mockito.doNothing().when(representativeRepository).deleteById(Mockito.anyLong());

        RepresantiveController represantiveController = new RepresantiveController(representativeServices, representativeRepository);
        represantiveController.deleteRepresentativeById(1l);

        assert (representative.getName()!=null);
        assert (representative.getRepresentative_Id() == 1l);
    }
}
