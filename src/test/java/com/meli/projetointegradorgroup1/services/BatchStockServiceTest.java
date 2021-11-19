package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BatchStockServiceTest {
    BatchStockItem batchStockItem = new BatchStockItem(1l, 2, 3.0, 4.0, 5.0,null, null );
    SellerService sellerService;
    Seller seller = new Seller();

    BatchStock batchStock = new BatchStock(1l, 2l,2.0,3.0,4.0,"5","6", LocalDateTime.now(), LocalDate.now(), 7, 8.0, batchStockItem,seller,null);
    BatchStockRequestDTO batchStockRequestDTO = new BatchStockRequestDTO(1l,2l,1l,2.0,3.0,4.0,"5","6", "2021-11-16 00:00:00",LocalDate.now(), 7, 8.0);
    BatchStockResponseDTO batchStockResponseDTO = new BatchStockResponseDTO(2l,null,2.0,3.0,4.0,"5","6", "2021-11-16 00:00:00",LocalDate.now(), 7, 8.0);

    BatchStockService batchStockService = Mockito.mock(BatchStockService.class);
    BatchStockRepository batchStockRepository = Mockito.mock(BatchStockRepository.class);
    BatchStockItemService batchStockItemService = Mockito.mock(BatchStockItemService.class);;
    List<BatchStock> list = new ArrayList();

    String message = null;


    @Test
    public void valida(){
        Mockito.doNothing().when(batchStockItemService).validaBatchStockItem(Mockito.anyLong());
        batchStockService = new BatchStockService(batchStockItemService, null, null );
        batchStockService.valida(1l);
        assert (batchStock.getBatchStockItem().getId() == 1);
    }

    @Test
    public void saveOk(){
        Mockito.when(batchStockRepository.save(Mockito.any())).thenReturn(batchStock);
        batchStockService = new BatchStockService(null, batchStockRepository, null);
        assert (batchStockService.save(batchStock).getBatchStockNumber() == 2);
    }

    @Test
    public void saveNok(){
        Mockito.when(batchStockRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        batchStockService = new BatchStockService(null, batchStockRepository, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockService.save(null);});
        message = "Erro na gravação Stock:";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void findBatchSotckOk(){
        list.add(batchStock);
        Mockito.when(batchStockRepository.findAll()).thenReturn(list);
        batchStockService = new BatchStockService(null, batchStockRepository, null);
        batchStockService.findBatchSotck();
        assert (list.size() == 1);

    }

    @Test
    public void findBatchSotckNok(){ ;
        Mockito.when(batchStockRepository.findAll()).thenReturn(list);
        batchStockService = new BatchStockService(null, batchStockRepository, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockService.findBatchSotck();});
        message = "Não existem Stock cadastradas";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void deleta(){
        Mockito.doNothing().when(batchStockRepository).deleteById(Mockito.anyLong());
        batchStockService = new BatchStockService(null, batchStockRepository, null);
        batchStockService.deleta(1l);
        assert (batchStock.getId() ==1 );
    }

    @Test
    public void deletaNok(){
        Mockito.doNothing().when(batchStockRepository).deleteById(Mockito.anyLong());
        batchStockService = new BatchStockService(null, null, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockService.deleta(1l);});
        message = "Erro ao deletar BatchStock";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void updateBatchStockOk(){
        sellerService = Mockito.mock(SellerService.class);
        Mockito.when(batchStockItemService.obter(Mockito.anyLong())).thenReturn(batchStockItem);
        Mockito.when(sellerService.obter(Mockito.anyLong())).thenReturn(seller);
        batchStockService = new BatchStockService(batchStockItemService, null, sellerService );
        assert (batchStockService.updateBatchStock(batchStock, batchStockRequestDTO).getBatchStockNumber().equals(batchStockRequestDTO.getBatchStockNumber()));
    }

    @Test
    public void updateBatchStockNok(){
        batchStockService = new BatchStockService(null, null, null);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        batchStockService.updateBatchStock(null, null);});
        message = "Representante não encontrado";
        assert (exception.getMessage().contains(message));
    }

    @Test
    public void convert(){
        sellerService = Mockito.mock(SellerService.class);
        Mockito.when(batchStockItemService.obter(Mockito.anyLong())).thenReturn(batchStockItem);
        Mockito.when(sellerService.obter(Mockito.anyLong())).thenReturn(seller);
        batchStockService = new BatchStockService(batchStockItemService, null, sellerService );
        assert (batchStockService.convert(batchStockRequestDTO, batchStockItemService, sellerService).getBatchStockNumber().equals(batchStockRequestDTO.getBatchStockNumber()));
    }

    @Test
    public void convertList(){
        list.add(batchStock);
        batchStockService = new BatchStockService(null, null, null );
        batchStockService.convertList(list);
        assert (batchStock.getBatchStockNumber().equals(batchStockResponseDTO.getBatchStockNumber()));
    }

    @Test
    public void convertToDto(){
        batchStockService = new BatchStockService(null, null, null );
        assert (batchStockService.convertToDto(batchStock).getBatchStockNumber().equals(batchStock.getBatchStockNumber()));
    }

    @Test
    public void findByIdOK(){
        Mockito.when(batchStockRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(batchStock));
        batchStockService = new BatchStockService(null, batchStockRepository, null );
        batchStockService.findById(1l);
        assert (batchStock.getId() == 1);
    }

    @Test
    public void findByIdNoK(){
        Mockito.when(batchStockRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(null));
        batchStockService = new BatchStockService(null, batchStockRepository, null );
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
        batchStockService.findById(1l);});
        message = "BatchStock não cadastrada";
        assert (exception.getMessage().contains(message));
    }
}
