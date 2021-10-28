package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.SectionDTO;
import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchstockItemResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
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

    public List<BatchstockItemResponseDTO> getBatchStockItemsList(){
        return batchStockItemRepository.findAll()
                .stream()
                .map(BatchstockItemResponseDTO::new)
                .collect(Collectors.toList());
    }

//    valida selller
    public void validSellerExist(BatchStockItemRequestDTO batchStockItemRequestDTO) {
        sellerService.valida(Long.parseLong(batchStockItemRequestDTO.getSeller_id()));

    }

    public BatchstockItemResponseDTO convertEntityToDTO(BatchStockItem batchStockItem){
        BatchstockItemResponseDTO batchstockItemResponseDTO = new BatchstockItemResponseDTO();
        batchstockItemResponseDTO.setQuantity(batchStockItem.getQuantity());
        return batchstockItemResponseDTO;
    }

    //validacao update por ID
    public BatchStockItem validaUpdate(Optional<BatchStockItem> batchStockItemFind, BatchStockItem batchStockItem) {
        if (batchStockItemFind.isPresent()) {
            BatchStockItem _batchStockItem = batchStockItemFind.get();
            _batchStockItem.setQuantity(batchStockItem.getQuantity());
            _batchStockItem.setBatchstock(batchStockItem.getBatchstock());


            return _batchStockItem;
        }else{
            throw new RuntimeException("BatchStockItem não encontrado");
        }
    }

}