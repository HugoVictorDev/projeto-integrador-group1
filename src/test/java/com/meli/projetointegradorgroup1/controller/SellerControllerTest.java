package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


class SellerControllerTest {
    SellerRepository sellerRepository;
    SellerService sellerService;

    // -- MASSA PARA OS TESTES
    Seller seller = new Seller(1l,"Edenilson", "36843012809","edenilson.paschoal@mercadolivre.com");
    SellerRequestDTO sellerRequestDTO = new SellerRequestDTO("Edenilson","36843012809","edenilson.paschoal@mercadolivre.com");
    List<SellerResponseDTO> list = new ArrayList();

    // - ID Seller
    Long sellerId = 1L;
    Long sellerIdNok = 2L;
    // - DTOS
    SellerRequestDTO seller1RequestDTO = new SellerRequestDTO("Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");
    SellerResponseDTO sellerResponseDTO = new SellerResponseDTO("Edenilson0", "36843012809", "edenilson.paschoal@mercadolivre.com");

    ArrayList<SellerResponseDTO> sellerArrayList = new ArrayList<>();



    @Test
    void createSeller() {
        sellerService = Mockito.mock(SellerService.class);

        Mockito.when(sellerService.save(Mockito.any())).thenReturn(null);
        Mockito.when(sellerService.converte(Mockito.any())).thenReturn(null);
        Mockito.when(sellerService.convertToDto(Mockito.any())).thenReturn(sellerResponseDTO);

        SellerController sellerController = new SellerController(sellerService);
        assert (sellerController.createSeller(sellerRequestDTO) != null);
    }

    @Test
    void getSellerList() {
        list.add(sellerResponseDTO);
        sellerService = Mockito.mock(SellerService.class);

        Mockito.when(sellerService.getSellers()).thenReturn(list);
        SellerController sellerController = new SellerController(sellerService);
        assert (sellerController.getSellerList().size() == 1);
    }

    @Test
    void getSellerById() {
        sellerService = Mockito.mock(SellerService.class);

        Mockito.when(sellerService.obter(Mockito.anyLong())).thenReturn(seller);
        Mockito.when(sellerService.convertToDto(Mockito.any())).thenReturn(sellerResponseDTO);

        SellerController sellerController = new SellerController(sellerService);
        assert (!sellerController.getSellerById(1l).equals(null));
    }

    @Test
    void updateSeller() {
        sellerService = Mockito.mock(SellerService.class);

        Mockito.when(sellerService.obter(Mockito.anyLong())).thenReturn(seller);
        Mockito.when(sellerService.validaUpdate(Mockito.any(), Mockito.any())).thenReturn(seller);
        Mockito.when(sellerService.convertToDto(Mockito.any())).thenReturn(sellerResponseDTO);
        Mockito.when(sellerService.save(Mockito.any())).thenReturn(seller);

        SellerController sellerController = new SellerController(sellerService);
        assert (sellerController.updateSeller(1l, sellerRequestDTO).getCpf().equals(sellerRequestDTO.getCpf()));
    }

    @Test
    void deleteSellerById() {
        sellerService = Mockito.mock(SellerService.class);

        Mockito.when(sellerService.obter(Mockito.anyLong())).thenReturn(seller);
        Mockito.doNothing().when(sellerService).deleta(Mockito.anyLong());
        Mockito.when(sellerService.convertToDto(Mockito.any())).thenReturn(sellerResponseDTO);

        SellerController sellerController = new SellerController(sellerService);
        sellerController.deleteSellerById(1l);

        assert (seller.getId() == 1);
    }
}