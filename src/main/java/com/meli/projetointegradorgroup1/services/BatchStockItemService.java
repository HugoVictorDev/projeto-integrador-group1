package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.BatchstockItemResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchStockItemService {

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    public List<BatchstockItemResponseDTO> getBatchStockItemsList(){
        return batchStockItemRepository.findAll()
                .stream()
                .map(BatchstockItemResponseDTO::new)
                .collect(Collectors.toList());
    }



    public BatchstockItemResponseDTO convertEntityToDTO(BatchStockItem batchStockItem){
        BatchstockItemResponseDTO sellerResponseDTO = new BatchstockItemResponseDTO();
        sellerResponseDTO.setQuantity(sellerResponseDTO.getQuantity());
        sellerResponseDTO.setProductlist(sellerResponseDTO.getProductlist());
        sellerResponseDTO.setBatchstock(sellerResponseDTO.getBatchstock());
        return sellerResponseDTO;
    }

}
