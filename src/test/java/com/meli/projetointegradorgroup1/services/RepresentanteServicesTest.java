package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Marco Siqueiraa
 */

public class RepresentanteServicesTest {

    Representante representante = new Representante(1l, "Joao", "98765432178");
    RepresentanteDTO representatnedto = new RepresentanteDTO(1l, "Cassio", "98765432178");
    RepresentanteDTO representatneConverte = new RepresentanteDTO(1l, "Joao", "98765432178");
    RepresentanteServices representanteServices = Mockito.mock(RepresentanteServices.class);
    RepresentanteRepository representanteRepository = Mockito.mock(RepresentanteRepository.class);
    Representante representanteNull = new Representante();
    List<Representante> representanteList = new ArrayList();
    List<RepresentanteDTO> representanteListDTO = new ArrayList();

    String message = "";
    String uri = "http//Mock";

    @Test
    public void valida(){
        Mockito.doNothing().when(representanteServices).validarCpf(Mockito.anyString());
        representanteServices = new RepresentanteServices(representanteRepository);
        representanteServices.valida(representatnedto);
        Assert.assertEquals(representatnedto.getCpf(), representante.getCpf());
    }

    @Test
    public void validaCpfNok(){
        Mockito.when(representanteRepository.findAllByCpf(Mockito.anyString())).thenReturn(representante);
        representanteServices = new RepresentanteServices(representanteRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{ representanteServices.validarCpf("17721898709");});
        message = "CPF já cadstrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOK(){
        Mockito.when(representanteServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(representante);
        representanteServices = new RepresentanteServices(null);
        representanteServices.validaUpdate(representante,representatnedto);
        assert (representante.getName().equals(representatnedto.getName()));
    }

    @Test
    public void validaUpdateNOK(){
        Mockito.when(representanteServices.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(null);
        representanteServices = new RepresentanteServices(null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            representanteServices.validaUpdate(representanteNull,representatnedto);});
        message = "Representante não encontrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void obterOk(){
        Mockito.when(representanteRepository.findByid(Mockito.any())).thenReturn(representante);
        representanteServices = new RepresentanteServices(representanteRepository);
        assert (representanteServices.obter(1l).getId() == 1);
    }

    @Test
    public void obterNok(){
        Mockito.when(representanteRepository.findByid(Mockito.any())).thenReturn(null);
        representanteServices = new RepresentanteServices(representanteRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            representanteServices.obter(null);});
        message = "Representante não encontrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void listarepresentatneOK(){
        representanteList.add(representante);
        Mockito.when(representanteServices.listaRepresentante()).thenReturn(representanteList);
        Mockito.when(representanteRepository.findAll()).thenReturn(representanteList);
        representanteServices = new RepresentanteServices(representanteRepository);
        assert (representanteServices.listaRepresentante().size() == 1);
    }

    @Test
    public void listarepresentatneNOK(){
        Mockito.when(representanteServices.listaRepresentante()).thenReturn(null);
        Mockito.when(representanteRepository.findAll()).thenReturn(representanteList);
        representanteServices = new RepresentanteServices(representanteRepository);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            representanteServices.listaRepresentante();});
        message = "Não existem Representantes cadastradas";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void converteOk(){

        RepresentanteServices representanteServices = new RepresentanteServices( null);
        representanteServices.convert(representatneConverte);
        assert (representanteServices.convert(representatneConverte).getName().equals(representatneConverte.getName()));
    }

    @Test
    public void converteToDto(){
        representanteServices = Mockito.mock(RepresentanteServices.class);
        RepresentanteServices representanteServices = new RepresentanteServices( null);
        assert (representanteServices.convertToDto(representante).getRepresentatne_Id().equals(representante.getId()));

    }

    @Test
    public void converteListOk(){
        representanteListDTO.add(representatneConverte);
        representanteList.add(representante);
        Mockito.when(this.representanteServices.convertList(Mockito.any())).thenReturn(representanteListDTO);
        RepresentanteServices representanteServices = new RepresentanteServices( null);
        representanteServices.convertList(representanteList);
        assert (representante.getName().equals(representatneConverte.getName()));
    }

    @Test
    public void deletarepresentatneOk(){
        Mockito.doNothing().when(representanteRepository).deleteById(Mockito.anyLong());
        RepresentanteServices representanteServices = new RepresentanteServices(representanteRepository);
        representanteServices.deletaRepresentante(1l);
        assert (representante.getId() == 1);
    }

    @Test
    public void saveOk(){
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(representanteRepository.save(Mockito.any())).thenReturn(representante);
        RepresentanteServices representanteServices = new RepresentanteServices(representanteRepository);
        assert (representanteServices.save(representante, uriBuilder).getStatusCodeValue() == 201 );
    }

    @Test
    public void saveNok(){
        Mockito.when(representanteRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        RepresentanteServices representanteServices = new RepresentanteServices(representanteRepository);
        assert (representanteServices.save(representante, null).getStatusCodeValue() == 400 );
    }
}
