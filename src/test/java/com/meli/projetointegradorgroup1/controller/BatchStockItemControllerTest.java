package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class BatchStockItemControllerTest
{
    BatchStockItemRequestDTO batchStockItemRequestDTO = new BatchStockItemRequestDTO
            (10, 10.0, 10.0, 10.0, 1l, 1l);

    BatchStockItem batchStockItem = new BatchStockItem
            (11l, 11, 10.0, 10.0, 10.0, null, null, null);

    BatchStockItem batchStockItemUpdate = new BatchStockItem
            (12l, 11, 10.0, 10.0, 10.0, null, null, null);

    BatchStockItemResponseDTO batchStockItemResponseDTO = new BatchStockItemResponseDTO(batchStockItem);

    BatchStockItem batchStockItemNull = null;
    List<BatchStockItem> batchStockItemList = new ArrayList<BatchStockItem>();
    List<BatchStockItemResponseDTO> batchStockItemResponseDTOList = new ArrayList<BatchStockItemResponseDTO>();

    BatchStockItemRepository batchStockItemRepository;
    BatchStockItemService batchStockItemService;

    @Test
    public void createBatchStockItem()
    {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService, batchStockItemRepository);

        Mockito.doNothing().when(batchStockItemService).validSellerExist(Mockito.any());
        Mockito.doNothing().when(batchStockItemService).validProductExist(Mockito.any());

        Mockito.when(batchStockItemRepository.save(Mockito.any())).thenReturn(batchStockItem);

        BatchStockItemRequestDTO response = batchStockItemController.createBatchStockItem(batchStockItemRequestDTO);

        assert (response != null);
    }


    @Test
    public void getBatchStockItemList(){
        batchStockItemResponseDTOList.add(batchStockItemResponseDTO);

        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.when(batchStockItemService.getBatchStockItemsList()).thenReturn(batchStockItemResponseDTOList);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService, batchStockItemRepository);

        List<BatchStockItemResponseDTO> responseList = batchStockItemController.getList();

        assert (responseList != null && responseList.size() == 1);
    }

    @Test
    public void updateBatchStockItem(){
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.when(batchStockItemRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(batchStockItemUpdate));
        Mockito.when(batchStockItemService.validaUpdate(Mockito.any(),Mockito.any())).thenReturn(batchStockItemUpdate);
        Mockito.when(batchStockItemRepository.save(Mockito.any())).thenReturn(batchStockItemUpdate);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService, batchStockItemRepository);

        BatchStockItem responseUpdate = batchStockItemController
                .updateBatchStockItemID(batchStockItemUpdate.getId(), batchStockItemUpdate);

        assert (responseUpdate.getQuantity() == batchStockItemUpdate.getQuantity());
    }

    @Test
    public void getBatchStockItemById(){
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.when(batchStockItemRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(batchStockItem));
        Mockito.when(batchStockItemService.convertEntityToDTO(Mockito.any())).thenReturn(batchStockItemResponseDTO);

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService, batchStockItemRepository);

        BatchStockItemResponseDTO response = batchStockItemController.getBatchStockItemById(1l);

        assert (response.getVolume() == batchStockItemResponseDTO.getVolume());
    }

    @Test
    public void deleteRepresentativeById() {
        batchStockItemService = Mockito.mock(BatchStockItemService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.doNothing().when(batchStockItemRepository).deleteById(Mockito.anyLong());

        BatchStockItemController batchStockItemController = new BatchStockItemController(batchStockItemService, batchStockItemRepository);

        ResponseEntity<HttpStatus> response = batchStockItemController.deleteBatchStockItemById(1l);

        assert (response.getStatusCode().equals(HttpStatus.NO_CONTENT));
    }
}
