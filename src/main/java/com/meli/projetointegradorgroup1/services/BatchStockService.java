package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockService {
    @Autowired
    private BatchStockRepository batchStockRepository;
    @Autowired
    private BatchStockItemService batchStockItemService;
    @Autowired
    private ProductService productService;


    public void update(BatchStock batchStock){
        Optional<BatchStock> _batchStock = batchStockRepository.findById(batchStock.getBatchStockNumber());
        if (_batchStock.isPresent()) {
            batchStockItemService.validaBatchStockItem(batchStock.getProductID());
            batchStockRepository.save(batchStock);
        }else{
            throw new RuntimeException("BatchStok não encotrada");
        }
    }

    public void deleteById(Long id){
        batchStockRepository.deleteById(id);
    }

    public void valida(Long productID) {
         batchStockItemService.validaBatchStockItem(productID);
    }

    public BatchStock save(BatchStock batchStock) {
        batchStockRepository.save(batchStock);
        return batchStock;
    }

    public List<BatchStockResponseDTO> findBatchSotck() {
     return batchStockRepository.findAll()
             .stream()
             .map(BatchStockResponseDTO::new)
             .collect(Collectors.toList());
    }

    public Optional<BatchStock> findBatchSotckById(Long id) {
        Optional<BatchStock> batchStock = batchStockRepository.findById(id);
        if (batchStock.equals(Optional.empty()) || batchStock == null){
            throw new RuntimeException("BatchStock não cadatsra");
        }else {
            deleteById(id);
            return batchStock;
        }
    }

}
