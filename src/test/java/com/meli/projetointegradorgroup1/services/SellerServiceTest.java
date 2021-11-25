package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
/**
 * @author Marco Siqueiraa
 */

class SellerServiceTest {
    SellerRepository sellerRepository = Mockito.mock(SellerRepository.class);
    RepresentanteServices representanteServices = Mockito.mock(RepresentanteServices.class);

    Seller seller = new Seller(1l, "Edenilson", "36843012809", "edenilson.paschoal@mercadolivre.com");

    SellerRequestDTO sellerRequestDTO = new SellerRequestDTO("Edenilson", "36843012809", "edenilson.paschoal@mercadolivre.com");
    List<Seller> sellerArrayList = new ArrayList();

    String message = null ;
    String uri = "http//Mock";

    @Test
    void getSellers() {
        sellerArrayList.add(seller);
        Mockito.when(sellerRepository.findAll()).thenReturn(sellerArrayList);
        SellerService sellerService = new SellerService(sellerRepository, null);
        List<SellerResponseDTO> listaResult = sellerService.getSellers();
        Assert.assertEquals(1, listaResult.size());
    }

    @Test
    void getSellersNok() {
        Mockito.when(sellerRepository.findAll()).thenReturn(sellerArrayList);
        SellerService sellerService = new SellerService(sellerRepository, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sellerService.getSellers();});
        message = "Não existem Sellers cadastrados";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOk(){
        Mockito.when(representanteServices.maskCpf(Mockito.any())).thenReturn(seller.getCpf());
        SellerService sellerService = new SellerService(null, representanteServices);
        assert (sellerService.validaUpdate(seller, sellerRequestDTO).getCpf().equals(sellerRequestDTO.getCpf()));
    }

    @Test
    public void obterOk(){
        Mockito.when(sellerRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(seller));
        SellerService sellerService = new SellerService(sellerRepository, null);
        assert (sellerService.obtem(1l) != null);
    }

    @Test
    public void obterNok(){
        Mockito.when(sellerRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        SellerService sellerService = new SellerService(sellerRepository, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        sellerService.obtem(1l) ;});
        message = "Seller não encontrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void saveOk(){
        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));
        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller);
        SellerService sellerService = new SellerService(sellerRepository, null);
        assert (sellerService.save(seller, uriBuilder).getStatusCodeValue() == 201 );
    }


    @Test
    public void saveNok(){
        Mockito.when(sellerRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        SellerService sellerService = new SellerService(sellerRepository, null);
        assert (sellerService.save(seller, null).getStatusCodeValue() == 400 );
    }


    @Test
    public void validaCpf(){
        Mockito.when(sellerRepository.findByCpf(Mockito.any())).thenReturn(null);
        Mockito.when(representanteServices.maskCpf(Mockito.any())).thenReturn(null);
        SellerService sellerService = new SellerService(sellerRepository, representanteServices);
        assert (sellerService.validaCpf("36843003859"));

    }
    @Test
    public void validaCpfNok(){
        Mockito.when(sellerRepository.findByCpf(Mockito.any())).thenReturn(seller);
        Mockito.when(representanteServices.maskCpf(Mockito.any())).thenReturn(null);
        SellerService sellerService = new SellerService(sellerRepository, representanteServices);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sellerService.validaCpf("36843003859");});
        message = "Seller já cadastrado";
        assert (exception.getMessage().contains(message));
    }

    @Test
    void deletaOk() {
        doNothing().when(sellerRepository).deleteById(Mockito.anyLong());
        SellerService sellerService = new SellerService(sellerRepository, null);
        sellerService.deleta(1l);
        assert (seller.getId() == 1);
   }

    @Test
    public void converte(){
        Mockito.when(representanteServices.maskCpf(Mockito.any())).thenReturn(null);
        SellerService sellerService = new SellerService(null, representanteServices);
        assert (sellerService.convert(sellerRequestDTO).getName().equals(sellerRequestDTO.getName()));
    }

    @Test
    public void converteToDto(){
        SellerService sellerService = new SellerService(null, null);
        assert (sellerService.convertToDto(seller).getName().equals(seller.getName()));
    }

}