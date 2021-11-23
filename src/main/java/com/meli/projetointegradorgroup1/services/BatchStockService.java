package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;

import com.meli.projetointegradorgroup1.repository.BatchStockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchStockService {
    @Autowired
    private BatchStockRepository batchStockRepository;
    @Autowired
    private BatchStockItemService batchStockItemService;
    @Autowired
    private SellerService sellerService;


    public void valida(Long productID) {
        batchStockItemService.validaBatchStockItem(productID);
    }

    public BatchStock save(BatchStock batchStock) {
        try {
            batchStockRepository.save(batchStock);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação Stock:", e );
        }
        return batchStock;
    }

    public List<BatchStock> findBatchSotck() {
        List<BatchStock> batchStockList = batchStockRepository.findAll();
        if(batchStockList.size() == 0){
            throw new RuntimeException("Não existem Stock cadastradas");
        }return batchStockList;
    }


    public BatchStock findBatchNumber(Long batchNumber) {
        BatchStock batchStock = batchStockRepository.findByBatchStockNumber(batchNumber);
//        if(batchStock == null){
//            throw new RuntimeException("BatchStock não cadastrada");
//        }
        return batchStock;
    }

    public void deleta(Long id) {
        try {
            batchStockRepository.deleteById(id);
        } catch (RuntimeException e) {
            if (e.getCause().getCause().getMessage().contains("violates foreign key constraint ")) {
                throw new RuntimeException("violates foreign key constraint");
            } else {
                throw e;
            }
        }
    }

    public BatchStock updateBatchStock(BatchStock batchStockFind, BatchStockRequestDTO dto) {
        if (batchStockFind == null){
            throw new RuntimeException("Representante não encontrado");
        }else{
            BatchStock batchStockUpdate = batchStockFind;
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            batchStockUpdate.setBatchStockNumber(dto.getBatchStockNumber());
            batchStockUpdate.setBatchStockItem(batchStockItemService.obtem(dto.getBatchStockItem()));
            batchStockUpdate.setCurrentTemperature(dto.getCurrentTemperature());
            batchStockUpdate.setMinimumTemperature(dto.getMinimumTemperature());
            batchStockUpdate.setMaximumTemperature(dto.getMaximumTemperature());
            batchStockUpdate.setQuantity(dto.getQuantity());
            batchStockUpdate.setVolume(dto.getVolume());
            batchStockUpdate.setInitialQuality(dto.getInitialQuality());
            batchStockUpdate.setCurrentTemperature(dto.getCurrentTemperature());
            batchStockUpdate.setManufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(), fmt));
            batchStockUpdate.setDueDate(dto.getDueDate());
            batchStockUpdate.setSeller(sellerService.findSellerById(dto.getSellerId()));
            return batchStockUpdate;
        }
    }

    public BatchStock convert(BatchStockRequestDTO dto,
                              BatchStockItemService batchStockItemService, SellerService sellerService){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(batchStockItemService.obtem(dto.getBatchStockItem()))
                .currentTemperature(dto.getCurrentTemperature())
                .minimumTemperature(dto.getMinimumTemperature())
                .maximumTemperature(dto.getMaximumTemperature())
                .quantity(dto.getQuantity())
                .volume(dto.getVolume())
                .initialQuality(dto.getInitialQuality())
                .currentQuality(dto.getCurrentQuality())
                .manufacturingTime(LocalDateTime.parse(dto.getManufacturingTime(), fmt))
                .dueDate(dto.getDueDate())
                .seller(sellerService.findSellerById(dto.getSellerId()))
                .build();
    }

    public List<BatchStockResponseDTO> convertList(List<BatchStock> batchSotcks) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        List<BatchStockResponseDTO> ListBatchStock = new ArrayList();
        for (BatchStock batchStock : batchSotcks) {
            ListBatchStock.add(
                    BatchStockResponseDTO.builder()
                            .batchStockNumber(batchStock.getBatchStockNumber())
                            .currentTemperature(batchStock.getCurrentTemperature())
                            .minimumTemperature(batchStock.getMinimumTemperature())
                            .maximumTemperature(batchStock.getMaximumTemperature())
                            .quantity(batchStock.getQuantity())
                            .volume(batchStock.getVolume())
                            .initialQuality(batchStock.getInitialQuality())
                            .currentQuality(batchStock.getCurrentQuality())
                            .manufacturingTime(batchStock.getManufacturingTime().format(formatter))
                            .dueDate(batchStock.getDueDate())
                            .sellerId(batchStock.getSeller().getId())
                            .build());
        }
        return ListBatchStock;
    }

    public BatchStockResponseDTO convertToDto(BatchStock batchStock) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return BatchStockResponseDTO.builder()
                .batchStockNumber(batchStock.getBatchStockNumber())
                .sellerId(batchStock.getSeller().getId())
                .currentTemperature(batchStock.getCurrentTemperature())
                .minimumTemperature(batchStock.getMinimumTemperature())
                .maximumTemperature(batchStock.getMaximumTemperature())
                .initialQuality(batchStock.getInitialQuality())
                .currentQuality(batchStock.getCurrentQuality())
                .manufacturingTime(batchStock.getManufacturingTime().format(formatter))
                .dueDate(batchStock.getDueDate())
                .sellerId(batchStock.getSeller().getId())
                .build();
    }

}