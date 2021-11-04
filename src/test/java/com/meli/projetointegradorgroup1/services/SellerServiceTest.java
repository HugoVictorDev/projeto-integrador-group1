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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellerServiceTest {

    // -- MOCK DA REPOSITORY
    SellerRepository repositoryMock = Mockito.mock(SellerRepository.class);
    // -- MASSA PARA OS TESTES
    Seller seller1 = Seller.builder().cpf("36843012809").name("Edenilson0").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller2 = Seller.builder().cpf("36843012889").name("Edenilson1").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller3 = Seller.builder().cpf("36843003859").name("Edenilson2").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller4 = Seller.builder().cpf("36843012809").name("Edenilson3").email("edenilson.paschoal@mercadolivre.com").build();
    // - ID Seller
    Long sellerId = 1L;
    Long sellerIdNok = 2L;
    // - DTOS
    SellerRequestDTO seller1RequestDTO = new SellerRequestDTO("Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");
    SellerResponseDTO seller1ResponseDTO = new SellerResponseDTO("Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");

    ArrayList<Seller> sellers = new ArrayList();

    @BeforeEach
    void setUp() {

    }

    @Test
    void setSeller() { // - CADASTRO DE SELLER

        Seller sellerReturn = new Seller();
        List<Seller> sellerArrayList = new ArrayList();
        sellerArrayList.add(seller1);

        Mockito.when(repositoryMock.save(seller1)).thenReturn(seller1);
        SellerService sellerService = new SellerService(repositoryMock);

        sellerReturn = sellerService.setSeller(seller1);
        Assert.assertEquals(seller1,sellerReturn);
    }

    @Test
    void getSellers() { // - CONSULTA TODOS SELLERS
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
    void valida() { // - VALIDA SE O SELLER EXISTE

        Mockito.when(repositoryMock.findBySellerId(sellerId)).thenReturn(seller1);

        SellerService sellerService = new SellerService(repositoryMock);
        boolean findSellerResult = sellerService.valida(sellerId);
        Assert.assertTrue(findSellerResult);
    }

    @Test
    void notValida() { // - TESTE DE NAO OK

        Mockito.when(repositoryMock.findBySellerId(sellerIdNok)).thenReturn(seller1);

        SellerService sellerService = new SellerService(repositoryMock);


        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sellerService.valida(sellerId);

        });
        String expectedMessage = "Seller não cadastrado";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void convertEntityToResponse() { // - CONVERSAO DE ENTIDADE PARA RESPONSE
        SellerService sellerService = new SellerService(repositoryMock);
        SellerResponseDTO sellerResponseDTO = sellerService.convertEntityToResponse(seller1); //validaUpdate(java.util.Optional.ofNullable(seller1), sellerRequestDTOSeller1);
        Assert.assertEquals(seller1ResponseDTO, sellerResponseDTO);

    }

    @Test
    void convertRequestDTOToEntity() { // - CONVERSAO DE ENTIDADE PARA REQUEST

        SellerService sellerService = new SellerService(repositoryMock);
        Seller seller = sellerService.convertRequestDTOToEntity(seller1RequestDTO); //validaUpdate(java.util.Optional.ofNullable(seller1), sellerRequestDTOSeller1);
        Assert.assertEquals(seller1, seller);

    }

    @Test
    void validaUpdate() { // - METODO QUE EFETUA A VALIDACAO DO UPDATE FEITO
        SellerService sellerService = new SellerService(repositoryMock);
        SellerResponseDTO findSellerResult = sellerService.validaUpdate(1L, seller1);
        Assert.assertEquals(seller1ResponseDTO, findSellerResult);
    }

    @Test
    void notvalidaUpdate() { // - METODO QUE EFETUA A VALIDACAO DO UPDATE FEITO
        SellerService sellerService = new SellerService(repositoryMock);


        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            sellerService.validaUpdate(2L,seller1);;

        });
        String expectedMessage = "Seller não encontrado";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));



    }

    @Test
    void convertEntityToDTORequest() {

        SellerService sellerService = new SellerService(repositoryMock);
        SellerRequestDTO sellerRequestDTO = sellerService.convertEntityToDTORequest(seller1); //validaUpdate(java.util.Optional.ofNullable(seller1), sellerRequestDTOSeller1);
        Assert.assertEquals(seller1RequestDTO, sellerRequestDTO);

    }
}