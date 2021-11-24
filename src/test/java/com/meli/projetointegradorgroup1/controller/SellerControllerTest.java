package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
//import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);
//
        Mockito.when(sellerService.setSeller(Mockito.any(),Mockito.any())).thenReturn(seller1);

        Mockito.when(sellerRepository.save(Mockito.any())).thenReturn(seller1);


        SellerController sellerController = new SellerController(sellerService);
        Seller sellerReturn =  sellerController.createSeller(seller1,null);

        assert (sellerReturn != null);


    }

    @Test
    void getSellerList() {
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);

        List<Seller> sellerList = new ArrayList();
        sellerList.add(seller1);
        sellerList.add(seller2);

        Mockito.when(sellerRepository.findAll()).thenReturn(sellerList);
        SellerService sellerService = new SellerService(sellerRepository);

        SellerController sellerController = new SellerController(sellerService);
        List<SellerResponseDTO> sellerListRet = sellerController.getSellerList();


        assert (sellerListRet.size() >= 1 );
    }

    @Test
    void getSellerById() {
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);

        sellerArrayList.add(seller1ResponseDTO);

        Mockito.when(sellerRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(seller1));
        Mockito.when(sellerService.findSellerById(Mockito.anyLong())).thenReturn(seller1);

        SellerController sellerController = new SellerController(sellerService);
        Seller sellerReturn = sellerController.getSellerById(sellerId);

        assert(sellerReturn.equals(seller1));
    }

    @Test
    void updateSeller() {
        sellerService = Mockito.mock(SellerService.class);
        sellerRepository = Mockito.mock(SellerRepository.class);

        Mockito.when(sellerService.update(Mockito.any(), Mockito.any())).thenReturn((ResponseEntity<HttpStatus>) status().isOk());

        SellerController sellerController = new SellerController(sellerService);
        ResponseEntity<HttpStatus> sellerReturn =  sellerController.updateSeller(1L,seller1);

        Assert.assertTrue(sellerReturn.getStatusCodeValue() == 200 );
    }

    @Test
    void deleteAllSellers() {


        ResponseEntity<HttpStatus> sellerReturn = sellerService.delAllSellers();


        List<SellerResponseDTO> Return = sellerService.getSellers();
        Assert.assertEquals( null, Return);




    }

    @Test
    void deleteSellerById() {

        sellerService.delAllSellers();

        List<SellerResponseDTO> sellerReturn = sellerService.getSellers();
        Assert.assertEquals( null, sellerReturn);

    }
}