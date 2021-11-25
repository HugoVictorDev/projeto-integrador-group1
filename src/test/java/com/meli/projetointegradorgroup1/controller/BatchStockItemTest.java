package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.StockType;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BatchStockItemTest {
    BatchStockItemRepository batchStockItemRepository;
    BatchStockItemService batchStockItemService;
    ProductService productService;
    StockType stockType;

    // -- MASSA PARA OS TESTES
    Product product = Product.builder().name("Picanha").stockType(stockType.FRESH).description("Carne bovina Maturata").build();

    BatchStockItem batchStockItem1 = BatchStockItem.builder().maximumTemperature(25.0).minimumTemperature(5.0).quantity(12).volume(10).product(product).build();
    BatchStockItem batchStockItem2 = BatchStockItem.builder().maximumTemperature(25.0).minimumTemperature(5.0).quantity(12).volume(10).product(product).build();
    BatchStockItem batchStockItem3 = BatchStockItem.builder().maximumTemperature(25.0).minimumTemperature(5.0).quantity(12).volume(10).product(product).build();
    BatchStockItem batchStockItem4 = BatchStockItem.builder().maximumTemperature(25.0).minimumTemperature(5.0).quantity(12).volume(10).product(product).build();
    // - ID BatchStockItem
    Long batchStockItemId = 1L;
    Long batchStockItemIdNok = 2L;
    // - DTOS
    BatchStockItemRequestDTO batchStockItem1RequestDTO = new BatchStockItemRequestDTO(12,10.0,25.00,5.0,1L);
    BatchStockItemResponseDTO batchStockItem1ResponseDTO = new BatchStockItemResponseDTO(12,10.0,25.0,5.0,1L,1L);

    ArrayList<BatchStockItemResponseDTO> batchStockItemArrayList = new ArrayList<>();



    @Test
    void createBatchStockItem() {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);
//
        Mockito.when(batchStockItemService.setBatchStockItem(Mockito.any(),Mockito.any())).thenReturn(batchStockItem1);

        Mockito.when(batchStockItemRepository.save(Mockito.any())).thenReturn(batchStockItem1);


        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService);
        BatchStockItem batchStockItemReturn =  batchStockItemController.createBatchStockItem(batchStockItem1,null);

        assert (batchStockItemReturn != null);


    }

    @Test
    void getBatchStockItemList() {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        List<BatchStockItem> batchStockItemList = new ArrayList();
        batchStockItemList.add(batchStockItem1);
        batchStockItemList.add(batchStockItem2);

        Mockito.when(batchStockItemRepository.findAll()).thenReturn(batchStockItemList);
        BatchStockItemService batchStockItemService = new BatchStockItemService(batchStockItemRepository);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService);
        List<BatchStockItemResponseDTO> batchStockItemListRet = batchStockItemController.getBatchStockItemList();


        assert (batchStockItemListRet.size() >= 1 );
    }

    @Test
    void getBatchStockItemById() {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemArrayList.add(batchStockItem1ResponseDTO);

        Mockito.when(batchStockItemRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(batchStockItem1));
        Mockito.when(batchStockItemService.findBatchStockItemById(Mockito.anyLong())).thenReturn(batchStockItem1);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService);
        BatchStockItem batchStockItemReturn = batchStockItemController.getBatchStockItemById(batchStockItemId);

        assert(batchStockItemReturn.equals(batchStockItem1));
    }

    @Test
    void updateBatchStockItem() {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
//        Mockito.when(batchStockItemService.update(Mockito.any(), Mockito.any())).thenReturn((ResponseEntity<HttpStatus>) status().isOk());

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService);


        //   Assert.assertEquals(true, batchStockItemController.updateBatchStockItem(1L, batchStockItem1) );
    }



    @Test
    void deleteBatchStockItemById() {

        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);
//
        Mockito.when(batchStockItemService.delBatchStockItem(Mockito.anyLong())).thenReturn((ResponseEntity<HttpStatus>) status().isOk());

        Mockito.when(batchStockItemRepository.save(Mockito.any())).thenReturn(batchStockItem1);


        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService);
        ResponseEntity batchStockItemReturn =  batchStockItemController.deleteBatchStockItemById(1L);

        Assert.assertTrue(batchStockItemReturn.getStatusCodeValue() == 200 );


    }
