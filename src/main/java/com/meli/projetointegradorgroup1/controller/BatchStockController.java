package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.request.BatchStockRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import com.meli.projetointegradorgroup1.services.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;
/**
 * @author Marco Siqueira
 */

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
    @ApiOperation(value = "Cadastrar novo BatchStock")
    public ResponseEntity<Object> createBatchStock (@RequestBody @Valid BatchStockRequestDTO batchStockRequestDTO, UriComponentsBuilder uriBuilder){
        batchStockService.valida(batchStockRequestDTO.getBatchStockItem());
        BatchStock batchStock = batchStockService.convert(batchStockRequestDTO, batchStockItemService, sellerService);
        return batchStockService.save(batchStock, uriBuilder);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de BastchStock")
    public List<BatchStockResponseDTO> listBastchStock(){
        return batchStockService.convertList(batchStockService.findBatchSotck());
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "Retornar BastchStock único a partir do id")
    public BatchStockResponseDTO listBastchStockID(@PathVariable("id") Long id) {
        return batchStockService.convertToDto(batchStockService.findByIds(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar BatchStock a partir do id")
    public BatchStockResponseDTO deleteBatchStockNumber(@PathVariable("id") Long id) {
           BatchStock batchStock = batchStockService.findByIds(id);
           batchStockService.deleta(batchStock.getId());
           return batchStockService.convertToDto(batchStock);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Atualizar BatchStock a partir do id")
    public ResponseEntity<Object> updateBatchStockNumber(@PathVariable("id") Long id, @RequestBody @Valid BatchStockRequestDTO batchStockDTO, UriComponentsBuilder uriBuilder) {
           BatchStock batchStockFind = batchStockService.findByIds(id);
           BatchStock batchStock = batchStockService.updateBatchStock(batchStockFind, batchStockDTO);
        return batchStockService.save(batchStock, uriBuilder);
    }
}
