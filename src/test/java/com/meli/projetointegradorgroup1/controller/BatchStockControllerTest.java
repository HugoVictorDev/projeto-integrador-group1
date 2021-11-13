package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.BatchStockDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BatchStockControllerTest {

    BatchStock batchStock = new BatchStock(1l, 2l,2.0,3.0,4.0,"5","6", LocalDateTime.now(),LocalDate.now(), 7, 8.0, null,null,null);
    BatchStockDTO batchStockDTO = new BatchStockDTO(1l,2l,1l,2.0,3.0,4.0,"5","6", "0000-00-00 00:00:00",LocalDate.now(), 7, 8.0);
    BatchStock batchStockUpdate = new BatchStock(2l, 1l,2.0,3.0,4.0,"5","6", LocalDateTime.now(),LocalDate.now(), 7, 8.0,null,null,null);

    BatchStockService batchStockService;
    List<BatchStock> list = new ArrayList();

    @Test
    public void createBatchStock(){
        batchStockService = Mockito.mock(BatchStockService.class);

        Mockito.doNothing().when(batchStockService).valida(Mockito.anyLong());
        Mockito.when(batchStockService.save(Mockito.any())).thenReturn(batchStock);
        Mockito.when(batchStockService.convert(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(batchStock);

        BatchStockController batchStockController = new BatchStockController(batchStockService);
        batchStockController.createBatchStock(batchStockDTO);

        assert (batchStock.getId()!= null);
    }

    @Test
    public void listBastchStock(){
        list.add(batchStock);
        batchStockService = Mockito.mock(BatchStockService.class);

        Mockito.when(batchStockService.findBatchSotck()).thenReturn(list);

        BatchStockController batchStockController = new BatchStockController(batchStockService);
        batchStockController.listBastchStock();

        assert (list.size() == 1);
    }

    @Test
    public void listBastchStockNumber(){
        batchStockService = Mockito.mock(BatchStockService.class);

        Mockito.when(batchStockService.findBatchNumber(Mockito.anyLong())).thenReturn(batchStock);
        Mockito.when(batchStockService.convertToDto(Mockito.any())).thenReturn(batchStockDTO);

        BatchStockController batchStockController = new BatchStockController(batchStockService);
        batchStockController.listBastchStockNumber(2l);

        assert (batchStock.getBatchStockNumber() == 2);
    }

    @Test
    public void deleteBatchStockNumber(){
        batchStockService = Mockito.mock(BatchStockService.class);

        Mockito.when(batchStockService.findBatchNumber(Mockito.anyLong())).thenReturn(batchStock);
        Mockito.doNothing().when(batchStockService).deleta(Mockito.anyLong());
        Mockito.when(batchStockService.convertToDto(Mockito.any())).thenReturn(batchStockDTO);

        BatchStockController batchStockController = new BatchStockController(batchStockService);
        batchStockController.deleteBatchStockNumber(2l);

        assert(batchStock.getId() != null);
    }

    @Test
    public void updateBatchStockNumber(){
        batchStockService = Mockito.mock(BatchStockService.class);

        Mockito.when(batchStockService.findBatchNumber(Mockito.anyLong())).thenReturn(batchStockUpdate);
        Mockito.when(batchStockService.updateBatchStock(Mockito.any(), Mockito.any())).thenReturn(batchStockUpdate);
        Mockito.when(batchStockService.save(Mockito.any())).thenReturn(batchStockUpdate);
        Mockito.when(batchStockService.convertToDto(Mockito.any())).thenReturn(batchStockDTO);

        BatchStockController batchStockController = new BatchStockController(batchStockService);
        batchStockController.updateBatchStockNumber(batchStockDTO);

        assert(batchStockUpdate.getBatchStockNumber().equals(batchStockDTO.getBatchStockNumber()));
    }

}
