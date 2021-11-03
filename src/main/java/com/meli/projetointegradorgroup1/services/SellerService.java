package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
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

    public void valida(Long sellerId) {
        Seller seller =  sellerRepository.findBySellerId(sellerId);
        if (seller == null){
            throw new RuntimeException("Seller não cadastrado");
        }
    }


    public SellerResponseDTO convertEntityToDTO(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCpf(seller.getCpf());
        sellerResponseDTO.setEmail(seller.getEmail());
        return sellerResponseDTO;
    }


    public Seller validaUpdate(Optional<Seller> sellerFind, SellerRequestDTO sellerRequestDTO) {
        if (sellerFind.isPresent()) {
            Seller _seller = sellerFind.get();
            _seller.setName(sellerRequestDTO.getName());
            _seller.setCpf(sellerRequestDTO.getCpf());
            _seller.setEmail(sellerRequestDTO.getEmail());

            return _seller;
        }else{
            throw new RuntimeException("Seller não encontrado");
        }
    }


    public SellerRequestDTO convertEntityToDTORequest(Seller seller){
        SellerRequestDTO sellerRequestDTO = new SellerRequestDTO();
        sellerRequestDTO.setName(seller.getName());
        sellerRequestDTO.setCpf(seller.getCpf());
        sellerRequestDTO.setEmail(seller.getEmail());
        return sellerRequestDTO;
    }

    public Seller obter(Long id){
        Optional<Seller> byId = this.sellerRepository.findById(id);
        return byId.get();
    }
}