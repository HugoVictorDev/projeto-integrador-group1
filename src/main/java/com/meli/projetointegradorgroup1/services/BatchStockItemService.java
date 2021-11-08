package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;

import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockItemService {

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    @Autowired
    SellerService sellerService;

    @Autowired
    ProductService productService;

    public BatchStockItem obtem(Long id){
        Optional<BatchStockItem> byId = this.batchStockItemRepository.findById(id);
        return byId.get();
    }

    public List<BatchStockItemResponseDTO> getBatchStockItemsList(){
        return batchStockItemRepository.findAll()
                .stream()
                .map(BatchStockItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    public BatchStockItemResponseDTO convertEntityToDTO(BatchStockItem batchStockItem){
        BatchStockItemResponseDTO batchstockItemResponseDTO = new BatchStockItemResponseDTO();
        batchstockItemResponseDTO.setQuantity(batchStockItem.getQuantity());
        batchstockItemResponseDTO.setVolume(batchStockItem.getVolume());
        batchstockItemResponseDTO.setMaximumTemperature(batchStockItem.getMaximumTemperature());
        batchstockItemResponseDTO.setMinimumTemperature(batchStockItem.getMinimumTemperature());

        return batchstockItemResponseDTO;
    }


    //    valida product
    public void validProductExist(BatchStockItemRequestDTO batchStockItemRequestDTO) {
        productService.valida(batchStockItemRequestDTO.getProduct_id());

    }



    //validacao update por ID
    public BatchStockItem validaUpdate(Optional<BatchStockItem> batchStockItemFind, BatchStockItem batchStockItem) {
        if (batchStockItemFind.isPresent()) {
            BatchStockItem _batchStockItem = batchStockItemFind.get();
            _batchStockItem.setQuantity(batchStockItem.getQuantity());
//            _batchStockItem.setBatchstock(batchStockItem.getBatchstock());


            return _batchStockItem;
        }else{
            throw new RuntimeException("BatchStockItem não encontrado");
        }
    }

    public void validaBatchStockItem(Long productID) {
        Optional<BatchStockItem> batchStockItem = batchStockItemRepository.findById(productID);
        if (batchStockItem == null || batchStockItem.equals(Optional.empty())){
            throw new RuntimeException("BatchStokItem não encotrada");
        }
    }


}