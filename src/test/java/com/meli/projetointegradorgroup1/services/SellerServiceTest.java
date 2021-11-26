package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.devtools.remote.server.HttpStatusHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class SellerServiceTest {

    // -- MOCK DA REPOSITORY
    SellerRepository repositoryMock = Mockito.mock(SellerRepository.class);

    // -- MASSA PARA OS TESTES
    Seller seller1 = Seller.builder().id(Long.valueOf(1)).cpf("36843012809").name("Edenilson0").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller2 = Seller.builder().cpf("36843012889").name("Edenilson1").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller3 = Seller.builder().cpf("36843003859").name("Edenilson2").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller4 = Seller.builder().cpf("36843012809").name("Edenilson3").email("edenilson.paschoal@mercadolivre.com").build();
    // - ID Seller
    Long sellerId = 1L;
    Long sellerIdNok = 2L;
    // - DTOS
    SellerRequestDTO seller1RequestDTO = new SellerRequestDTO("Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");
    SellerResponseDTO seller1ResponseDTO = new SellerResponseDTO(null, "Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");


    String message = "";
    String uri = "http//Mock";

    ArrayList<Seller> sellers = new ArrayList();

    @BeforeEach
    void setUp() {

    }

    @Test
    void setSeller() { // - CADASTRO DE SELLER - OK

        UriComponentsBuilder uriBuilder;
        uriBuilder = Mockito.mock(UriComponentsBuilder.class);
        Mockito.when(uriBuilder.path(Mockito.anyString())).thenReturn(UriComponentsBuilder.fromPath(uri));

        Seller sellerReturn = new Seller();
        List<Seller> sellerArrayList = new ArrayList();
        sellerArrayList.add(seller1);

        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(seller1);
        SellerService sellerService = new SellerService(repositoryMock);

        sellerReturn = sellerService.setSeller(seller1,uriBuilder);
        Assert.assertEquals(seller1,sellerReturn);
    }

    @Test
    void deleteSellerOK() { // - delete de SELLER - OK

        Seller sellerReturn = new Seller();
        List<Seller> sellerArrayList = new ArrayList();
        sellerArrayList.add(seller1);
        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(seller1));
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(seller1ResponseDTO);
        doNothing().when(repositoryMock).deleteById(1L);
        SellerService sellerService = new SellerService(repositoryMock);

        ResponseEntity<HttpStatus> deleteReturn = sellerService.delSeller(1L);
        Assert.assertTrue(deleteReturn.getStatusCodeValue() == 200 );
    }

//    @Test
//    void deleteSellerNOK() { // - delete de SELLER - OK
//
//        Seller sellerReturn = new Seller();
//        List<Seller> sellerArrayList = new ArrayList();
//        sellerArrayList.add(seller1);
//        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(null);
//        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(null);
//
//        SellerService sellerService = new SellerService(repositoryMock);
//
//        ResponseEntity<HttpStatus> deleteReturn = sellerService.delSeller(2L);
//        Assert.assertTrue(deleteReturn.getStatusCodeValue() == 404 );
//    }


    @Test
    void getSellers() { // - CONSULTA TODOS SELLERS - OK
        List<Seller> sellerArrayList = new ArrayList();

        sellerArrayList.add(seller1);
        sellerArrayList.add(seller2);
        sellerArrayList.add(seller3);
        sellerArrayList.add(seller4);


        Mockito.when(repositoryMock.findAll()).thenReturn(sellerArrayList);

        SellerService sellerService = new SellerService(repositoryMock);

        List<SellerResponseDTO> listaResult = sellerService.getSellers();

        Assert.assertEquals(4, listaResult.size());
    }
    @Test
    void convertEntityToDTO(){
        SellerService sellerService = new SellerService(repositoryMock);
        SellerResponseDTO sellerResponseDTO = sellerService.convertEntityToDTO(seller1);
        Assert.assertEquals(seller1ResponseDTO, sellerResponseDTO);
    }

    @Test
    void convertEntityToDTORequest() {

        SellerService sellerService = new SellerService(repositoryMock);
        SellerRequestDTO sellerRequestDTO = sellerService.convertEntityToDTORequest(seller1); //validaUpdate(java.util.Optional.ofNullable(seller1), sellerRequestDTOSeller1);
        Assert.assertEquals(seller1RequestDTO, sellerRequestDTO);

    }

    @Test
    void update() { // - CADASTRO DE SELLER - OK

        Seller sellerReturn = new Seller();
        List<Seller> sellerArrayList = new ArrayList();
        sellerArrayList.add(seller1);

        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(seller1));
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(seller1);
        SellerService sellerService = new SellerService(repositoryMock);

        ResponseEntity<HttpStatus> Return = sellerService.update(seller1);

        Assert.assertTrue(Return.getStatusCodeValue() == 200 );
    }
}