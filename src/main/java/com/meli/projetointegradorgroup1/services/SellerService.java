package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<Seller> getSellers(){
        return sellerRepository.findAll();
    }

}
