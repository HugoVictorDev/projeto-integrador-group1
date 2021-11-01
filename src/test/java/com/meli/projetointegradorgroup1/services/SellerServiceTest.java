package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellerServiceTest {
    SellerRepository repositoryMock = Mockito.mock(SellerRepository.class);
    // -- Massa de testes para os cenarios
    Seller seller1 = Seller.builder().cpf("36843012809").name("Edenilson0").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller2 = Seller.builder().cpf("36843012809").name("Edenilson1").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller3 = Seller.builder().cpf("36843012809").name("Edenilson2").email("edenilson.paschoal@mercadolivre.com").build();
    Seller seller4 = Seller.builder().cpf("36843012809").name("Edenilson3").email("edenilson.paschoal@mercadolivre.com").build();

    ArrayList<Seller> sellers = new ArrayList();

    @BeforeEach
    void setUp() {

    }

    @Test
    void setSeller() {
        Seller sellerReturn = new Seller();
        List<Seller> sellerArrayList = new ArrayList();
        sellerArrayList.add(seller1);

        Mockito.when(repositoryMock.save(seller1)).thenReturn(seller1);
        SellerService sellerService = new SellerService(repositoryMock);

        sellerReturn = sellerService.setSeller(seller1);
        Assert.assertEquals(seller1,sellerReturn);
    }

    @Test
    void getSellers() {
        List<Seller> sellerArrayList = new ArrayList();

        sellerArrayList.add(seller1);
        sellerArrayList.add(seller2);
        sellerArrayList.add(seller3);
        sellerArrayList.add(seller4);


        Mockito.when(repositoryMock.findAll()).thenReturn(sellerArrayList);

        SellerService sellerService = new SellerService(repositoryMock);

        List<Seller> listaResult = sellerService.getSellers();

        Assert.assertEquals(4, listaResult.size());
    }

    @Test
    void valida() {

    }

    @Test
    void convertEntityToResponse() {
    }

    @Test
    void convertRequestDTOToEntity() {
    }

    @Test
    void validaUpdate() {
    }

    @Test
    void convertEntityToDTORequest() {
    }
}