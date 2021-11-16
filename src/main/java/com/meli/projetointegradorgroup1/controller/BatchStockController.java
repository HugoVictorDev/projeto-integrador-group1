package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import com.meli.projetointegradorgroup1.services.SellerService;
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
    RepresentanteRepository representanteRepository;
    @Autowired
    private BatchStockService batchStockService;
    @Autowired
    private BatchStockRepository batchStockRepository ;

    @Autowired
    private BatchStockItemService batchStockItemService;

    @Autowired
    private SellerService sellerService;


    @PostMapping("/create")
    public BatchStockRequestDTO createBatchStock (@Valid @RequestBody BatchStockRequestDTO batchStockRequestDTO){
//           batchStockService.valida(batchStockDTOhugo.getBatchStockItem());
            this.batchStockRepository.save(batchStockService.convert(batchStockRequestDTO,
                    batchStockItemService, sellerService));
            return batchStockRequestDTO;
    }

    @GetMapping("/list/{number}")
    public BatchStockResponseDTO listBastchStockNumber(@PathVariable("number") Long BatchNumber) {
        return batchStockService.convertToDto(batchStockService.findBatchNumber(BatchNumber));
    }


    @DeleteMapping("/delete/{number}")
    public BatchStockResponseDTO deleteBatchStockNumber(@PathVariable("number") Long BatchNumber) {
        BatchStock batchStock = batchStockService.findBatchNumber(BatchNumber);
        batchStockService.deleta(batchStock.getId());
        return batchStockService.convertToDto(batchStock);
    }

    @PutMapping("/update")
    public BatchStockResponseDTO updateBatchStockNumber(@RequestBody @Valid BatchStockRequestDTO batchStockDTO) {
        BatchStock batchStockFind = batchStockService.findBatchNumber(batchStockDTO.getBatchStockNumber());
        BatchStock batchStock = batchStockService.updateBatchStock(batchStockFind, batchStockDTO);
        return batchStockService.convertToDto(batchStockService.save(batchStock));

        //      Optional<InBoundOrder> _batchStock = inboundOrderRepository.findById(batchStock.getBatchStockNumber());
        //      if (_batchStock.isPresent()) {
        //          return new ResponseEntity<>(HttpStatus.CREATED);
        //      } else {
        //          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //      }
    }
}
