package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.BatchStockDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/batchstock")
public class BatchStockController {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    private BatchStockService batchStockService;

    private BatchStockRepository batchStockRepository;


    @PostMapping("/create")
    public BatchStockResponseDTO createBatchStock (@Valid @RequestBody BatchStockDTO batchStockDTO){
           batchStockService.valida(batchStockDTO.getBatchStockItem());
           BatchStock batchStock = BatchStockDTO.converte(batchStockDTO);
           return BatchStockResponseDTO.converte(batchStockService.save(batchStock));
    }

    @GetMapping("/list")
    public List<BatchStockResponseDTO> listBastchStock(){
           return batchStockService.findBatchSotck();
    }


    @DeleteMapping("/delete/{id}")
    public Optional<BatchStock> deleteBatchStockNumber(@PathVariable("id") Long id) {
           Optional<BatchStock> batchStock = batchStockService.findBatchSotckById(id);
           return batchStock;
    }

    @PutMapping("/update")
    public BatchStockDTO updateBatchStockNumber(@RequestBody BatchStockDTO batchStockDTO) {
           BatchStock batchStock = BatchStockDTO.converte(batchStockDTO);
           batchStockService.update(batchStock);
           return batchStockDTO;
    }
}
