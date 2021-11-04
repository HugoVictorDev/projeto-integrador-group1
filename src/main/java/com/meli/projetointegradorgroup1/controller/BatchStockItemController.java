package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;

import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/batchstockitem")
public class BatchStockItemController {

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    @Autowired
    BatchStockItemService batchStockItemService;

    public BatchStockItemController(BatchStockItemService batchStockItemService, BatchStockItemRepository batchStockItemRepository) {
        this.batchStockItemService = batchStockItemService;
        this.batchStockItemRepository = batchStockItemRepository;
    }

    //Cadastrar BatchStockItem

    @PostMapping("/create")
    public BatchStockItemRequestDTO createBatchStockItem(@Valid @RequestBody BatchStockItemRequestDTO batchStockItemRequestDTO) {
        batchStockItemService.validSellerExist(batchStockItemRequestDTO);
        batchStockItemService.validProductExist(batchStockItemRequestDTO);
        this.batchStockItemRepository.save(batchStockItemRequestDTO.build());
        return batchStockItemRequestDTO;
    }


    //Consultar lista de  vendedores
    @GetMapping("/list")
    List<BatchStockItemResponseDTO> getList() {
        return batchStockItemService.getBatchStockItemsList();
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public BatchStockItemResponseDTO getBatchStockItemById(@PathVariable("id") Long id) {
        return batchStockItemService.convertEntityToDTO(batchStockItemRepository.getById(id));
//
    }

    // atualizando vendedor pelo ID
    @PutMapping("/update/{id}")
    public BatchStockItem updateBatchStockItemID(@PathVariable("id") Long id, @RequestBody BatchStockItem batchStockItem) {
        Optional<BatchStockItem> batchStockItemFind = batchStockItemRepository.findById(id);
        BatchStockItem _bat = batchStockItemService.validaUpdate(batchStockItemFind, batchStockItem);


        return batchStockItemRepository.save(_bat);

    }

    //delete todos vendedores
    @DeleteMapping("/deleteall")
    public BatchStockItem deleteAllBatchStockItems() {
        batchStockItemRepository.deleteAll();
        return null;

    }

    //deletar vendedor pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBatchStockItemById(@PathVariable("id") Long id) {
        try {
            batchStockItemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}