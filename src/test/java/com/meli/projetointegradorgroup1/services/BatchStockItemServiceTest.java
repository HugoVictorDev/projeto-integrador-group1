package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
/**
 * @author Edenilson Pascoa
 */


public class BatchStockItemServiceTest {
    // -- MOCK DA REPOSITORY
    BatchStockItemRepository repositoryMock = Mockito.mock(BatchStockItemRepository.class);
    // -- MOCK DO SERVICE
    BatchStockItemService serviceMock = Mockito.mock(BatchStockItemService.class);

    // -- MASSA PARA OS TESTES
    BatchStockItem batchStockItem1 = BatchStockItem.builder().id(1L).quantity(12).volume(1.0).maximumTemperature(1.0).minimumTemperature(0.0).product(null).batchStock(null).build();
    BatchStockItem batchStockItem2 = BatchStockItem.builder().id(2L).quantity(12).volume(1.0).maximumTemperature(1.0).minimumTemperature(0.0).product(Product.builder().build().setId(1L)).batchStock(BatchStock.builder().id(1L).build()).build();
    BatchStockItem batchStockItem3 = BatchStockItem.builder().id(3L).quantity(12).volume(1.0).maximumTemperature(1.0).minimumTemperature(0.0).product(Product.builder().build().setId(1L)).batchStock(BatchStock.builder().id(1L).build()).build();
    BatchStockItem batchStockItem4 = BatchStockItem.builder().id(4L).quantity(12).volume(1.0).maximumTemperature(1.0).minimumTemperature(0.0).product(Product.builder().build().setId(1L)).batchStock(BatchStock.builder().id(1L).build()).build();


    // - ID BatchStockItem
    Long batchStockItemId = 1L;
    Long batchStockItemIdNok = 2L;
    // - DTOS
    BatchStockItemRequestDTO batchStockItemRequestDTO1 = new BatchStockItemRequestDTO(12,1D,1.0,0.0,1l);
    BatchStockItemResponseDTO batchStockItemResponseDTO1 = new BatchStockItemResponseDTO(12,1D,1D,0D,null);

    ArrayList<Seller> sellers = new ArrayList();



    @BeforeEach
    void setUp() {

    }

/*    @Test
    void getBatchStockItem() {


        List<BatchStockItem> batchStockItemList = new ArrayList();
        batchStockItemList.add(batchStockItem1);

        Mockito.when(repositoryMock.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(batchStockItem1));
        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);

        BatchStockItem  batchStockItemReturn = batchStockItemService.getBatchStockItem(1L);

        Assert.assertEquals(batchStockItem1,batchStockItemReturn);

    }*/

//    @Test
//    void getBatchStockItemsList() {
//        List<BatchStockItem> batchStockItemArrayList = new ArrayList();
//
//        batchStockItemArrayList.add(batchStockItem1);
//        batchStockItemArrayList.add(batchStockItem2);
//        batchStockItemArrayList.add(batchStockItem3);
//        batchStockItemArrayList.add(batchStockItem4);
//
//
//        Mockito.when(repositoryMock.findAll()).thenReturn(batchStockItemArrayList);
//
//        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);
//        List<BatchStockItemResponseDTO> listaResult = batchStockItemService.getBatchStockItemsList();
//
//        Assert.assertEquals(4, listaResult.size());
//    }

//    @Test
//    void convertEntityToDTO() {
//
//        Mockito.when( serviceMock.convertEntityToDTO(batchStockItem1)).thenReturn(batchStockItemResponseDTO1);
//
//        BatchStockItemService batchStockItemService = new BatchStockItemService(repositoryMock);
//        BatchStockItemResponseDTO batchStockItemResponseDTO = batchStockItemService.convertEntityToDTO(batchStockItem1);
//        Assert.assertEquals(batchStockItemResponseDTO1, batchStockItemResponseDTO);
//    }



    @Test
    void validaUpdate() {

    }

    @Test
    void validaBatchStockItem() {

    }
}
