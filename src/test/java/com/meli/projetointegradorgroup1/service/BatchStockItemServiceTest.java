package com.meli.projetointegradorgroup1.service;

import com.meli.projetointegradorgroup1.controller.BatchStockItemController;
import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.ProductService;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BatchStockItemServiceTest {
    BatchStockItemRequestDTO batchStockItemRequestDTO = new BatchStockItemRequestDTO
            (10, 10.0, 10.0, 10.0, 1000l, 1l);

    BatchStockItem batchStockItem = new BatchStockItem
            (11l, 11, 10.0, 10.0, 10.0, null, null, null);

    BatchStockItem batchStockItemUpdate = new BatchStockItem
            (12l, 11, 10.0, 10.0, 10.0, null, null, null);

    Optional<BatchStockItem> batchStockItemFindOk = Optional.of(new BatchStockItem
            (12l, 22, 15.0, 15.0, 11.0, null, null, null));

    Optional<BatchStockItem> batchStockItemFindNull = null;

    BatchStockItem batchStockItemNull = null;

    BatchStockItemResponseDTO batchStockItemResponseDTO = new BatchStockItemResponseDTO(batchStockItem);

    List<BatchStockItem> batchStockItemList = new ArrayList<BatchStockItem>();
    List<BatchStockItemResponseDTO> batchStockItemResponseDTOList = new ArrayList<BatchStockItemResponseDTO>();

    SellerService sellerService;
    ProductService productService;

    BatchStockItemRepository batchStockItemRepository;

    BatchStockItemService batchStockItemService;

    String message = "null";

    @Test
    public void getBatchStockItemListOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        batchStockItemList.add(batchStockItem);

        Mockito.when(batchStockItemRepository.findAll()).thenReturn(batchStockItemList);

        List<BatchStockItemResponseDTO> responseList = batchStockItemService.getBatchStockItemsList();

        assert (responseList != null && responseList.size() == 1);
    }

    @Test
    public void convertEntityToDTOOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        BatchStockItemResponseDTO response = batchStockItemService.convertEntityToDTO(batchStockItem);

        assert (response != null
                && response.getVolume() == batchStockItem.getVolume()
                && response.getQuantity() == batchStockItem.getQuantity()
                && response.getMinimumTemperature() == batchStockItem.getMinimumTemperature()
                && response.getMaximumTemperature() == batchStockItem.getMaximumTemperature()
        );
    }

    @Test
    public void validSellerExistOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.doNothing().when(sellerService).valida(Mockito.anyLong());

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        boolean exception = false;

        try
        {
            batchStockItemService.validSellerExist(batchStockItemRequestDTO);
        }
        catch (Exception ex)
        {
            exception = true;
        }

        assert (!exception);
    }

    @Test
    public void validSellerExistNOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockItemService.validSellerExist(batchStockItemRequestDTO);});

        message = "Seller não cadastrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validProductExistOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        Mockito.doNothing().when(productService).valida(Mockito.anyLong());

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        boolean exception = false;

        try
        {
            batchStockItemService.validProductExist(batchStockItemRequestDTO);
        }
        catch (Exception ex)
        {
            exception = true;
        }

        assert (!exception);
    }

    @Test
    public void validProductExistNOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockItemService.validProductExist(batchStockItemRequestDTO);});

        message = "Produto não cadastrado.";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaUpdateOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        BatchStockItem response = batchStockItemService.validaUpdate(batchStockItemFindOk, batchStockItem);

        assert (response.getQuantity() == batchStockItem.getQuantity());
    }

    @Test
    public void validaUpdateNOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockItemService.validaUpdate(batchStockItemFindNull, batchStockItem);});

        message = "BatchStockItem não encontrado";
        assert (message.contains(exception.getMessage()));
    }

    @Test
    public void validaBatchStockItemOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        Mockito.when(batchStockItemRepository.findById(Mockito.anyLong())).thenReturn(batchStockItemFindOk);

        boolean exception = false;

        try
        {
            batchStockItemService.validaBatchStockItem(11l);
        }
        catch (Exception ex)
        {
            exception = true;
        }

        assert (!exception);
    }

    @Test
    public void validaBatchStockItemNOK(){
        sellerService = Mockito.mock(SellerService.class);
        productService = Mockito.mock(ProductService.class);
        batchStockItemRepository = Mockito.mock(BatchStockItemRepository.class);

        batchStockItemService = new BatchStockItemService(batchStockItemRepository, sellerService, productService);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->{
            batchStockItemService.validaBatchStockItem(11l);});

        message = "BatchStokItem não encotrada";
        assert (!message.contains(exception.getMessage()));
    }
}