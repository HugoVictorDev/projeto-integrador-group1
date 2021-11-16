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

    public BatchStock saveBS(BatchStock batchStock){
        Optional<BatchStock> _representative = batchStockRepository.findById(batchStock.getBatchStockNumber());
        if (_representative.isPresent()) {
            BatchStock representative = _representative.get();
            BatchStock _batchStock = batchStockRepository.save(batchStock);
            return _batchStock;
        }
        return null;
    }

    public void deleteBS(Long BatchNumber){
        batchStockRepository.deleteBybatchStockNumber(BatchNumber);
    }

    public void updateIBO(BatchStock batchStock){
        batchStockRepository.save(batchStock);
    }

    public void valida(Long productID) {
        batchStockItemService.validaBatchStockItem(productID);
    }

    public BatchStock save(BatchStock batchStock) {
        batchStockRepository.save(batchStock);
        return batchStock;
    }

//    public List<BatchStockResponseDTO> findBatchSotck() {
//        return batchStockRepository.findAll()
//                .stream()
//                .map(BatchStockResponseDTO::new)
//                .collect(Collectors.toList());
//        //TODO: revisar
//        return null;
//    }
}