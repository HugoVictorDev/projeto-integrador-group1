package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/batchstockitem")
public class BatchStockItemController {

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    //Cadastrar vendedor
    @PostMapping("/create")
    public ResponseEntity<BatchStockItem> createBatchStockItem (@RequestBody BatchStockItem batchStockItem){
        try {
            BatchStockItem _BatchStockItem = batchStockItemRepository.save(new BatchStockItem(batchStockItem.getQuantity(), batchStockItem.getProductlist(), batchStockItem.getBatchstock()));
            return new ResponseEntity<>(_BatchStockItem, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Consultar lista de  vendedores
    @GetMapping("/list")
    public ResponseEntity<List<BatchStockItem>> getBatchStockItemList() {
        try {
            List<BatchStockItem> batchStockItem = new ArrayList<BatchStockItem>();

            batchStockItemRepository.findAll().forEach(batchStockItem::add);

            return new ResponseEntity<>(batchStockItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public ResponseEntity<BatchStockItem> getBatchStockItemById(@PathVariable("id") Long id) {
        Optional<BatchStockItem> batchStockItemFind = batchStockItemRepository.findById(id);

        if (batchStockItemFind.isPresent()) {
            return new ResponseEntity<>(batchStockItemFind.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // atualizando vendedor pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<BatchStockItem> updateBatchStockItem(@PathVariable("id") Long id, @RequestBody BatchStockItem batchStockItem) {
        Optional<BatchStockItem> batchStockItemFind = batchStockItemRepository.findById(id);

        if (batchStockItemFind.isPresent()) {
            BatchStockItem _batchStockItemFind = batchStockItemFind.get();
            _batchStockItemFind.setQuantity(batchStockItem.getQuantity());
            _batchStockItemFind.setProductlist(batchStockItem.getProductlist());
            _batchStockItemFind.setBatchstock(batchStockItem.getBatchstock()); // tem que ver como fazer a tratativa de edicao da lista.
            return new ResponseEntity<>(batchStockItemRepository.save(_batchStockItemFind), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //delete todos vendedores
    @DeleteMapping("/deleteall")
    public ResponseEntity<HttpStatus> deleteAllBatchStockItem() {
        try {
            batchStockItemRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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

