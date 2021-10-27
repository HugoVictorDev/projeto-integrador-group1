package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class BatchStockService {
    @Autowired
    private BatchStockRepository batchStockRepository;
    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private InBoundOrderRepository inBORepository;


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

}
