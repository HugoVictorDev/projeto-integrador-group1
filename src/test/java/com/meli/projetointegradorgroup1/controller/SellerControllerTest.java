package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SellerControllerTest {
    SellerRepository sellerRepository;
    SellerService sellerService;

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


    @Test
    void createSeller() {

        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);


        Mockito.when(sellerService.convertRequestDTOToEntity(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerService.convertEntityToResponse(Mockito.any())).thenReturn(seller1ResponseDTO);
        Mockito.when(sellerService.setSeller(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);

        SellerController sellerController = new SellerController(sellerRepository, sellerService);
        SellerResponseDTO sellerResponseDTO = sellerController.createSeller(seller1RequestDTO);

        Assert.assertEquals(seller1ResponseDTO, sellerResponseDTO);


    }

    @Test
    void getSellerList() {
    }

    @Test
    void getSellerById() {
    }

    @Test
    void updateSeller() {
    }

    @Test
    void deleteAllSellers() {
    }

    @Test
    void deleteSellerById() {
    }
}