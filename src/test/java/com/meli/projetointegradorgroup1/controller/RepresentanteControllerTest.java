package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Marco Siqueiraa
 */

public class RepresentanteControllerTest {
    Representante representante = new Representante(1l, "Joao", "98765432178");
    Representante representanteUpdate = new Representante(1l, "Cassio", "98765432178");
    RepresentanteDTO representanteDTO = new RepresentanteDTO(null, "Cassio", "98765432178");
    RepresentanteServices representanteServices;
    RepresentanteRepository representanteRepository;
    List<Representante> representanteList = new ArrayList();
    List<RepresentanteDTO> representanteListDto;

    @Test
    public void createRepresentante(){
        representanteServices = Mockito.mock(RepresentanteServices.class);
        representanteRepository = Mockito.mock(RepresentanteRepository.class);
        Mockito.doNothing().when(representanteServices).valida(Mockito.any());
        Mockito.when(representanteServices.convert(Mockito.any())).thenReturn(representante);
        Mockito.when(representanteServices.convertToDto(Mockito.any())).thenReturn(representanteDTO);
        Mockito.when(representanteRepository.save(Mockito.any())).thenReturn(representante);
        RepresentanteController represantanteController = new RepresentanteController(representanteServices);
        represantanteController.createrepResentante(representanteDTO, null);
        assert (representante.getId() != null);
    }
    @Test
    public void getrepResentanteList(){
        representanteList.add(representante);
        representanteServices = Mockito.mock(RepresentanteServices.class);
        Mockito.when(representanteServices.listaRepresentante()).thenReturn(representanteList);
        Mockito.when(representanteServices.convertList(Mockito.anyList())).thenReturn(representanteListDto);
        RepresentanteController represantanteController = new RepresentanteController(representanteServices);
        represantanteController.getRepresentanteList();
        assert (representanteList.size() == 1);
    }
    @Test
    public void updateRepresentante(){
        representanteServices = Mockito.mock(RepresentanteServices.class);
        representanteRepository = Mockito.mock(RepresentanteRepository.class);
        Mockito.when(representanteServices.convertToDto(Mockito.any())).thenReturn(representanteDTO);
        Mockito.when(representanteServices.obter(Mockito.anyLong())).thenReturn(representanteUpdate);
        Mockito.when(representanteServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(representanteUpdate);
        Mockito.when(representanteRepository.save(Mockito.any())).thenReturn(representanteUpdate);
        RepresentanteController represantanteController = new RepresentanteController(representanteServices);
        represantanteController.updateRepresentante(representanteDTO, null);
        assert (representanteUpdate.getName().equals(representanteDTO.getName()));
    }
    @Test
    public void getRepresentanteById(){
        representanteServices = Mockito.mock(RepresentanteServices.class);
        Mockito.when(representanteServices.convertToDto(Mockito.any())).thenReturn(representanteDTO);
        Mockito.when(representanteServices.obter(Mockito.anyLong())).thenReturn(representante);
        RepresentanteController represantanteController = new RepresentanteController(representanteServices);
        represantanteController.getRepresentanteById(1l);

        assert (representante.getName()!=null);
    }
    @Test
    public void deleteRepresentatneById() {
        representanteServices = Mockito.mock(RepresentanteServices.class);
        Mockito.doNothing().when(representanteServices).deletaRepresentante(Mockito.anyLong());
        Mockito.when(representanteServices.obter(Mockito.anyLong())).thenReturn(representante);
        Mockito.when(representanteServices.convertToDto(Mockito.any())).thenReturn(representanteDTO);
        RepresentanteController represantanteController = new RepresentanteController(representanteServices);
        represantanteController.deleteRepresentanteById(1l);
        assert (representante.getId() == 1l);
    }
}
