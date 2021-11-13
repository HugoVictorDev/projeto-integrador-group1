package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.BatchStockDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.Section;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BatchStockService {
    @Autowired
    private BatchStockRepository batchStockRepository;
    @Autowired
    private BatchStockItemService batchStockItemService;



    public void valida(Long productID) {
        batchStockItemService.validaBatchStockItem(productID);
    }

    public BatchStock save(BatchStock batchStock) {
        try {
            batchStockRepository.save(batchStock);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação Section:", e );
        }
        return batchStock;
    }

    public List<BatchStock> findBatchSotck() {
        List<BatchStock> batchStockList = batchStockRepository.findAll();
        if(batchStockList.size() == 0){
            throw new RuntimeException("Não existem Sessões cadastradas");
        }return batchStockList;
    }

    public BatchStockDTO convertToDto(BatchStock batchStock) {
        return null;
    }


    public BatchStock findBatchNumber(Long batchNumber) {
        return  null;
    }

    public void deleta(Long id) {
    }

    public BatchStock updateBatchStock(BatchStock batchStockFind, BatchStockDTO batchStockDTO) {
        return null;
    }

    public BatchStock convert(BatchStockDTO dto,
                              BatchStockItemService batchStockItemService, SellerService sellerService){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return BatchStock.builder()
                .batchStockNumber(dto.getBatchStockNumber())
                .batchStockItem(batchStockItemService.obter(dto.getBatchStockItem()))
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

    public List<BatchStockDTO> convertList(List<BatchStock> batchSotcks) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        List<BatchStockDTO> ListBatchStock = new ArrayList();
        for (BatchStock batchStock : batchSotcks) {
            ListBatchStock.add(
                    BatchStockDTO.builder()
                            .batchStockNumber(batchStock.getBatchStockNumber())
                            .batchStockItem(batchStock.getBatchStockItem().getId())//verificar se é o id mesmo
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
}