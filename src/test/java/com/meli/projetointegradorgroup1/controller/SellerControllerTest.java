package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
//import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    ArrayList<SellerResponseDTO> sellerArrayList = new ArrayList<>();



    @Test
    void createSeller() {
        Seller seller1 = Seller.builder().cpf("36843012809").name("Edenilson0").email("edenilson.paschoal@mercadolivre.com").build();
        Seller seller =  sellerService.setSeller(seller1);

        Assert.assertEquals(seller1, seller);



    }

    @Test
    void getSellerList() {


        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);
        //SellerService sellerService = new SellerService(sellerRepository);

        SellerController sellerController = new SellerController(sellerRepository, sellerService);
        sellerController.getSellerList();

        assert (sellerService.getSellers().size() >= 1 );
    }

    @Test
    void getSellerById() {
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);

        sellerArrayList.add(seller1ResponseDTO);

        Mockito.when(sellerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(seller1));

        SellerController sellerController = new SellerController(sellerRepository,sellerService);


        //assertTrue (sellerReturn.equals(seller1ResponseDTO));
    }

    @Test
    void updateSeller() {
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);

        Mockito.when(sellerService.delSeller(Mockito.any())).thenReturn((ResponseEntity<HttpStatus>) status().isOk());
        //Mockito.when(sellerService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(seller1ResponseDTO);

        SellerController sellerController = new SellerController(sellerRepository,sellerService);
        //SellerResponseDTO sellerReturn =  sellerController.updateSeller(1L,seller1);

        //assertTrue (sellerReturn.equals(seller1ResponseDTO));
    }

    @Test
    void deleteAllSellers() {

        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);
        sellerArrayList.add(seller1ResponseDTO);

        Mockito.when(sellerService.getSellers()).thenReturn(sellerArrayList);
        //Mockito.when(sellerService.convertRequestDTOToEntity(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerService.convertEntityToDTO(Mockito.any())).thenReturn(seller1ResponseDTO);
        Mockito.when(sellerService.setSeller(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerService.delAllSellers()).thenReturn((ResponseEntity<HttpStatus>) status().isOk());
        //Mockito.when(sellerService.delSeller(Mockito.any())).thenReturn((ResponseEntity<HttpStatus>) status().isOk());
        //Mockito.when(sellerService.getSellers()).thenReturn(null);

        SellerController sellerController = new SellerController(sellerRepository, sellerService);
        Seller seller = sellerController.createSeller(seller1);



        sellerService.delAllSellers();

        List<SellerResponseDTO> sellerReturn = sellerController.getSellerList();
        System.out.printf(sellerReturn.toString());

        List<SellerResponseDTO> sellerReturn2 = sellerService.getSellers();
        Assert.assertEquals( null, sellerReturn);




    }

    @Test
    void notdeleteAllSellers() {

        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);
        sellerArrayList.add(seller1ResponseDTO);

        Mockito.when(sellerService.getSellers()).thenReturn(sellerArrayList);
        //Mockito.when(sellerService.convertRequestDTOToEntity(Mockito.any())).thenReturn(seller1);
        //Mockito.when(sellerService.convertEntityToResponse(Mockito.any())).thenReturn(seller1ResponseDTO);
        //Mockito.when(sellerService.setSeller(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);
        //Mockito.when(sellerService.deleteAllSellers()).thenReturn(true);;

        //SellerController sellerController = new SellerController(sellerRepository, sellerService);
        //SellerResponseDTO sellerResponseDTO = sellerController.createSeller(seller1RequestDTO);

        //List<SellerResponseDTO> sellerReturn = sellerController.getSellerList();
        //  System.out.printf(sellerReturn.toString());

        //sellerService.deleteAllSellers();

        Mockito.when(sellerService.getSellers()).thenReturn(null);

        List<SellerResponseDTO> sellerReturn2 = sellerService.getSellers();
        Assert.assertEquals( null, sellerReturn2);




    }
    @Test
    void deleteSellerById() {

        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);
        sellerArrayList.add(seller1ResponseDTO);

        Mockito.when(sellerService.getSellers()).thenReturn(sellerArrayList);
        //Mockito.when(sellerService.convertRequestDTOToEntity(Mockito.any())).thenReturn(seller1);
        //Mockito.when(sellerService.convertEntityToResponse(Mockito.any())).thenReturn(seller1ResponseDTO);
        //Mockito.when(sellerService.setSeller(Mockito.any())).thenReturn(seller1);
        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);
        //Mockito.when(sellerService.deleteSeller(Mockito.any())).thenReturn(true);;

        //SellerController sellerController = new SellerController(sellerRepository, sellerService);
        //SellerResponseDTO sellerResponseDTO = sellerController.createSeller(seller1RequestDTO);

        //sellerService.deleteSeller(1L);

        Mockito.when(sellerService.getSellers()).thenReturn(null);
        //List<SellerResponseDTO> sellerReturn = sellerController.getSellerList();
        //  System.out.printf(sellerReturn.toString());


        //

        List<SellerResponseDTO> sellerReturn2 = sellerService.getSellers();
        //Assert.assertEquals( null, sellerReturn);

    }
}