package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockService {
    @Autowired
    private BatchStockRepository batchStockRepository;
    @Autowired
    private BatchStockItemService batchStockItemService;

    BatchStockResponseDTO batchStockResponseDTO;

    public ResponseEntity<HttpStatus> save(BatchStock batchStock){
        BatchStock batchStockReturn = batchStockRepository.findById(batchStock.getBatchStockNumber()).get();
        if (batchStockReturn == null) {
            batchStock = batchStockRepository.save(batchStock);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<HttpStatus> deleteBybatchStockNumber (Long BatchNumber){
        //batchStockRepository.deleteBybatchStockNumber(BatchNumber); TODO implementar no repository
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteById (Long id){
        batchStockRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAll (Long id){
        batchStockRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> update(BatchStock batchStock){
        BatchStock batchStockReturn = batchStockRepository.findById(batchStock.getBatchStockNumber()).get();
        if (batchStockReturn != null) {
            batchStockRepository.save(batchStock);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BatchStockResponseDTO findById(Long id) {
        return batchStockResponseDTO.converte(batchStockRepository.findById(id).get());
    }

    public List<BatchStockResponseDTO> findAll() {
        List<BatchStock> batchStocks = batchStockRepository.findAll();
        List<BatchStockResponseDTO> batchStockResponseDTOS = new ArrayList();

        for (BatchStock bs: batchStocks) {
            batchStockResponseDTOS.add(BatchStockResponseDTO.builder()
                    .batchStockNumber(bs.getBatchStockNumber())
                    .currentTemperature(bs.getCurrentTemperature())
                    .minimumTemperature(bs.getMinimumTemperature())
                    .maximumTemperature(bs.getMaximumTemperature())
                    .initialQuality(bs.getInitialQuality())
                    .currentQuality(bs.getCurrentQuality())
                    .manufacturingTime(bs.getManufacturingTime())
                    .dueDate(bs.getDueDate())
                    .quantity(bs.getQuantity())
                    .volume(bs.getVolume())
                    .productID(bs.getProductID())
                    .batchStockItem(bs.getBatchStockItem())
                    .build());
        }

        return batchStockResponseDTOS;
    }
}