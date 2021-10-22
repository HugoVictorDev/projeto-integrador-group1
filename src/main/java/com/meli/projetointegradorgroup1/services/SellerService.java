package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<SellerResponseDTO> getSellers(){
        return sellerRepository.findAll()
                .stream()
                .map(SellerResponseDTO::new)
                .collect(Collectors.toList());
    }

    private SellerResponseDTO convertEntityToDTO(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCpf(seller.getCpf());
        return sellerResponseDTO;
    }

}
