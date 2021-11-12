package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.response.BatchStockResponseDTO;
import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.repository.BatchStockRepository;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.services.BatchStockItemService;
import com.meli.projetointegradorgroup1.services.BatchStockService;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/batchstock")
public class BatchStockController {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    RepresentativeRepository representativeRepository;
    @Autowired
    private BatchStockService batchStockService;
    @Autowired
    private BatchStockRepository batchStockRepository ;

    @Autowired
    private BatchStockItemService batchStockItemService;

    @Autowired
    private SellerService sellerService;


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createBatchStock (@RequestBody BatchStock batchStock){
            return batchStockService.save(batchStock);

    }

    @GetMapping("/list")
    public List<BatchStockResponseDTO> listBastchStock(){
           return batchStockService.findAll();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBatchStockNumber(@PathVariable("id") Long ordernumber) {
        batchStockService.deleteById(ordernumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id")
    public ResponseEntity<HttpStatus> updateBatchStockNumber(@RequestBody BatchStock batchStock) {

        //InBoundOrder inBoundOrder = new InBoundOrder();
        return batchStockService.save(batchStock);
    }
}
