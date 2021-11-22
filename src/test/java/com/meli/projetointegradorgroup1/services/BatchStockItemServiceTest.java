package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class BatchStockItemServiceTest {

    // -- MOCK DA REPOSITORY
    BatchStockItemRepository repositoryMock = Mockito.mock(BatchStockItemRepository.class);

    // -- MASSA PARA OS TESTES
    BatchStockItem BatchStockItem1 = BatchStockItem.builder().build();
    BatchStockItem BatchStockItem2 = BatchStockItem.builder().build();
    BatchStockItem BatchStockItem3 = BatchStockItem.builder().build();
    BatchStockItem BatchStockItem4 = BatchStockItem.builder().build();
    BatchStockItem BatchStockItem5 = BatchStockItem.builder().build();

    // - ID BatchStockItem
    Long BatchStockItemId = 1L;
    Long BatchStockItemIdNok = 2L;
    // - DTOS
    BatchStockItemRequestDTO batchStockItemRequestDTO1 = new BatchStockItemRequestDTO();
    BatchStockItemResponseDTO batchStockItemResponseDTO1 = new BatchStockItemResponseDTO();

    ArrayList<Seller> sellers = new ArrayList();



    @BeforeEach
    void setUp() {

    }

    @Test
    void getBatchStockItem() {

        BatchStockItem batchStockItemReturn  = new BatchStockItem();
        List<BatchStockItem> batchStockItemList = new ArrayList();
        batchStockItemList.add(BatchStockItem1);

        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(BatchStockItem1);
        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);

        //batchStockItemReturn = batchStockItemService.setBatchStockItem(BatchStockItem1);
        Assert.assertEquals(BatchStockItem1,batchStockItemReturn);

    }

    @Test
    void getBatchStockItemsList() {
        List<BatchStockItem> batchStockItemArrayList = new ArrayList();

        batchStockItemArrayList.add(BatchStockItem1);
        batchStockItemArrayList.add(BatchStockItem2);
        batchStockItemArrayList.add(BatchStockItem3);
        batchStockItemArrayList.add(BatchStockItem4);


        Mockito.when(repositoryMock.findAll()).thenReturn(batchStockItemArrayList);

        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);
        List<BatchStockItemResponseDTO> listaResult = batchStockItemService.getBatchStockItemsList();

        Assert.assertEquals(4, listaResult.size());
    }

    @Test
    void convertEntityToDTO() {
        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);
        BatchStockItemResponseDTO batchStockItemResponseDTO = batchStockItemService.convertEntityToDTO(BatchStockItem1);
        Assert.assertEquals(batchStockItemResponseDTO1, batchStockItemResponseDTO);
    }

    @Test
    void validProductExist() {

    }

    @Test
    void validaUpdate() {

    }

    @Test
    void validaBatchStockItem() {

    }
}