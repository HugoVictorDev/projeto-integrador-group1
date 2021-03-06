package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.BatchStockItemRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.BatchStockItemResponseDTO;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchStockItemService {

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    @Autowired
    SellerService sellerService;

    @Autowired
    BatchStockService batchStockService;


    @Autowired
    ProductService productService;

    public BatchStockItemService(BatchStockItemRepository repositoryMock) {
        this.batchStockItemRepository = repositoryMock;
    }

    public ResponseEntity<String>  setBatchStockItem(BatchStockItem batchStockItem, UriComponentsBuilder uriBuilder){
        Long IdProduct = batchStockItem.getProduct().getId();
        Long IdBatchStock = batchStockItem.getBatchStock().getId();

        if (IdProduct != 0){
            if (IdBatchStock != 0) {
                batchStockItem.setBatchStock(batchStockService.findBatchNumber(IdBatchStock));
                batchStockItem.setProduct(productService.obtem(IdProduct));
            }else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("IdBatchStock não encontrado");
            }
        }else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("IdProduct não encontrado");
        }

        batchStockItemRepository.save(batchStockItem);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("BatchStockItem created");
    }

    public BatchStockItem getBatchStockItem(Long id){
        BatchStockItem byId = batchStockItemRepository.findById(id).get();
        return byId;
    }

    public List<BatchStockItemResponseDTO> getBatchStockItemsList(){
        return batchStockItemRepository.findAll()
                .stream()
                .map(BatchStockItemResponseDTO::new)
                .collect(Collectors.toList());
    }

    public BatchStockItemResponseDTO convertEntityToDTO(BatchStockItem batchStockItem){
        BatchStockItemResponseDTO batchstockItemResponseDTO = new BatchStockItemResponseDTO();
        batchstockItemResponseDTO.setQuantity(batchStockItem.getQuantity());
        batchstockItemResponseDTO.setVolume(batchStockItem.getVolume());
        batchstockItemResponseDTO.setMaximumTemperature(batchStockItem.getMaximumTemperature());
        batchstockItemResponseDTO.setMinimumTemperature(batchStockItem.getMinimumTemperature());

        return batchstockItemResponseDTO;
    }

    public ResponseEntity<HttpStatus> delBatchStockItemById(Long id){
        try {
            batchStockItemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "BatchStockItem - Erro inesperado");
        }
    }

    //    valida product
    public void validProductExist(BatchStockItemRequestDTO batchStockItemRequestDTO) {
        productService.valida(batchStockItemRequestDTO.getProduct_id());
    }

    public BatchStockItem findBatchStockItemById(Long Id){
        return this.batchStockItemRepository.findById(Id).get();
    }



    //validacao update por ID
    public ResponseEntity<String> update(BatchStockItem batchStockItem) {
        BatchStockItem batchStockItemFind = batchStockItemRepository.findById(batchStockItem.getId()).get();
        if (batchStockItemFind == null ){
            throw new RuntimeException("BatchStockItem não encotrado");
        }
        Product productFind = productService.obtem(batchStockItem.getProduct().getId());
        if (productFind == null ){
            throw new RuntimeException("Product não encotrado");
        }
        BatchStock batchStockFind = batchStockService.findByIds(batchStockItem.getBatchStock().getId());
        if (batchStockFind == null ){
            return new ResponseEntity<String>( HttpStatus.NOT_FOUND);
        }

        batchStockItem.setProduct(productFind);
        batchStockItem.setBatchStock(batchStockFind);
        this.batchStockItemRepository.save(batchStockItem);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public void validaBatchStockItem(Long productID) {
        Optional<BatchStockItem> batchStockItem = batchStockItemRepository.findById(productID);
        if (batchStockItem == null || batchStockItem.equals(Optional.empty())){
            throw new RuntimeException("BatchStokItem não encotrada");
        }
    }


}