package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/batchstock")
public class BatchStockController {

    @Autowired
    private BatchStockService batchStockService;

    @Autowired
    private BatchStockItemService batchStockItemService;

    @Autowired
    private SellerService sellerService;

    public BatchStockController(BatchStockService batchStockService) {
        this.batchStockService = batchStockService;
    }


    @PostMapping("/create")
    public BatchStockRequestDTO createBatchStock (@RequestBody @Valid BatchStockRequestDTO batchStockRequestDTO){
           batchStockService.valida(batchStockRequestDTO.getBatchStockItem());
           batchStockService.save(batchStockService.convert(batchStockRequestDTO, batchStockItemService, sellerService));
           return batchStockRequestDTO;
    }

    @GetMapping("/list")
    public List<BatchStockResponseDTO> listBastchStock(){
        return batchStockService.convertList(batchStockService.findBatchSotck());
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
