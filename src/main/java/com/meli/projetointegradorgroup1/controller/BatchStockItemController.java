package com.meli.projetointegradorgroup1.controller;



import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;

import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.ProductService;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;

    public BatchStockItemController(BatchStockItemService batchStockItemService) {
        this.batchStockItemService = batchStockItemService;
    }

    //Cadastrar BatchStockItem

    @PostMapping("/create")
    public ResponseEntity<String> createBatchStockItem(@RequestBody BatchStockItem batchStockItem, UriComponentsBuilder uriBuilder) {
        return batchStockItemService.setBatchStockItem(batchStockItem, uriBuilder);
    }


    //Consultar lista de  vendedores
    @GetMapping("/list")
    List<BatchStockItemResponseDTO> getList() {
        return batchStockItemService.getBatchStockItemsList();
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public BatchStockItemResponseDTO getBatchStockItemById(@PathVariable("id") Long id) {
        return batchStockItemService.convertEntityToDTO(batchStockItemService.findBatchStockItemById(id));
//
    }

    // atualizando vendedor pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBatchStockItemID(@RequestBody BatchStockItem batchStockItem) {
        return batchStockItemService.update(batchStockItem);
    }

    //deletar vendedor pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBatchStockItemById(@PathVariable("id") Long id) {
        return batchStockItemService.delBatchStockItemById(id);
    }

}
