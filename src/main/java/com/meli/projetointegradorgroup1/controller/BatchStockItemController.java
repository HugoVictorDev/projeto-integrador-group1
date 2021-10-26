package com.meli.projetointegradorgroup1.controller;


import com.meli.projetointegradorgroup1.dto.response.BatchstockItemResponseDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.SellerService;
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

    //Cadastrar BatchStockItem
    @PostMapping("/create")
    public BatchStockItem createBatchStockItem(@Valid @RequestBody BatchStockItem batchStockItem){
       return this.batchStockItemRepository.save(batchStockItem);
    SellerRepository sellerRepository;

    //Cadastrar vendedor
    @PostMapping("/create")
    public ResponseEntity<BatchStockItem> createBatchStockItem (@RequestBody BatchStockItem batchStockItem){
//        List<Seller> seller = sellerRepository.findAll();

        try {
            BatchStockItem _BatchStockItem = batchStockItemRepository.save(new BatchStockItem(batchStockItem.getQuantity(),
                    batchStockItem.getProductlist(), batchStockItem.getBatchstock()));
            return new ResponseEntity<>(_BatchStockItem, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Consultar lista de  vendedores
    @GetMapping("/list")
    List<BatchstockItemResponseDTO> getBatchStockItemList() {
        return batchStockItemService.getBatchStockItemsList();
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public BatchstockItemResponseDTO getSellerById(@PathVariable("id") Long id) {
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



    //tratamento de mensagens de erro do bad request seguindo as regras do VALID
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        );
        return errors;
    }


}
