package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<SellerResponseDTO> getSellers(){
        return sellerRepository.findAll()
                .stream()
                .map(SellerResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Seller setSeller(Seller seller, UriComponentsBuilder uriBuilder){ // - ok
        URI uri = uriBuilder.path("/seller/{id}").buildAndExpand(seller.getId()).toUri();
        return sellerRepository.save(seller);
    }

    public ResponseEntity<HttpStatus> delSeller(Long id){// ok

        try {
            if(this.findSellerById(id) != null) {
                sellerRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public SellerResponseDTO convertEntityToDTO(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCpf(seller.getCpf());
        sellerResponseDTO.setEmail(seller.getEmail());
        return sellerResponseDTO;
    }

    public ResponseEntity<HttpStatus> update(Seller seller) {
        Seller seller1 = sellerRepository.findById(seller.getId()).get();

        try {
            if (seller != null) {
                seller1.setCpf(seller.getCpf());
                seller1.setEmail (seller.getEmail());
                seller1.setName(seller.getName());
                sellerRepository.save(seller1);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro não esperado");
        }
    }



    public SellerRequestDTO convertEntityToDTORequest(Seller seller){
        SellerRequestDTO sellerRequestDTO = new SellerRequestDTO();
        sellerRequestDTO.setName(seller.getName());
        sellerRequestDTO.setCpf(seller.getCpf());
        sellerRequestDTO.setEmail(seller.getEmail());
        return sellerRequestDTO;
    }

    public Seller findSellerById(Long id){
        return sellerRepository.findById(id).get();
    }

}