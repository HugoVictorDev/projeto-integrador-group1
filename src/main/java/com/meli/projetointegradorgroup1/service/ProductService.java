package com.meli.projetointegradorgroup1.service;

import com.meli.projetointegradorgroup1.dto.ProductDTO;
import com.meli.projetointegradorgroup1.entity.BatchStockItem;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.BatchStockItemRepository;
import com.meli.projetointegradorgroup1.repository.ProductRepository;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    BatchStockItemRepository batchStockItemRepository;

    public void validaProduct(ProductDTO productDTO){
        validaSellerId(Long.parseLong(productDTO.getSellerId()));
        validaBatchStockItem(Long.parseLong(productDTO.getBatchstockitemId()));
    }

    public void validaSellerId(Long sellerId){
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller == null){
            throw new RuntimeException("Vendedor nao cadastrado");
        }
    }

    public void validaBatchStockItem(Long batchStockItemId){
        Optional<BatchStockItem> batchStockItem = batchStockItemRepository.findById(batchStockItemId);
        if (batchStockItem == null){
            throw new RuntimeException("Estoque de Item nao cadastrado");
        }
    }

    public static String maskmanufacturingDate(String manufacturingDate){
        return (manufacturingDate.substring(0, 2) + " / " + manufacturingDate.substring(2, 4) + " / " + manufacturingDate.substring(4, 6));
    }
}
