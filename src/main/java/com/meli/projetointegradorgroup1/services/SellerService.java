package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Optional;
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

    public SellerResponseDTO setSeller(Seller seller){
        return convertEntityToDTO(sellerRepository.save(seller));
    }

    public ResponseEntity<HttpStatus> delSeller(Long id){// ok

        try {
            this.findSellerById(id);

            sellerRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller não valido");
        }
    }

    public ResponseEntity<HttpStatus> delAllSellers(){

        try {
            sellerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> valida(Long sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public SellerResponseDTO convertEntityToDTO(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCpf(seller.getCpf());
        sellerResponseDTO.setEmail(seller.getEmail());
        return sellerResponseDTO;
    }


    public SellerResponseDTO validaUpdate(Seller sellerFind, SellerRequestDTO sellerRequestDTO) {
        if (sellerFind != null) {
            Seller _seller = sellerFind;
            _seller.setName(sellerRequestDTO.getName());
            _seller.setCpf(sellerRequestDTO.getCpf());
            _seller.setEmail(sellerRequestDTO.getEmail());

            return this.convertEntityToDTO(_seller);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller não valido");
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
        Optional<Seller> _byId = sellerRepository.findById(id);

        return _byId.get();
    }
}