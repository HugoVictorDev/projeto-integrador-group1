package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class SellerService {


    private final SellerRepository sellerRepository;

    //public SellerResponseDTO setSellers(@Valid SellerRequestDTO sellerRequestDTO){
     //   this.sellerRepository.save(sellerRequestDTO.build());
     //   return SellerRequestDTO;
    //}

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller setSeller(Seller seller){
        this.sellerRepository.save(seller);
        return seller;
    }

    //
    public List<SellerResponseDTO> getSellers(){
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDTO> sellerResponseDTOS = new ArrayList<>();
        sellers.stream().forEach((seller) -> sellerResponseDTOS.add(this.convertEntityToResponse(seller)));

        return  sellerResponseDTOS;
    }

    public boolean valida(Long sellerId) {
        Optional<Seller> seller =  sellerRepository.findBySellerId(sellerId);
        if (seller == null){
            throw new RuntimeException("Seller não cadastrado");
        }
        return true;
    }

    //
    public SellerResponseDTO convertEntityToResponse(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setName(seller.getName());
        sellerResponseDTO.setCpf(seller.getCpf());
        sellerResponseDTO.setEmail(seller.getEmail());
        return sellerResponseDTO;
    }

    //
    public Seller convertRequestDTOToEntity(SellerRequestDTO sellerRequestDTO){
        Seller seller = new Seller();
        seller.setName(sellerRequestDTO.getName());
        seller.setCpf(sellerRequestDTO.getCpf());
        seller.setEmail(sellerRequestDTO.getEmail());
        return seller;
    }


    public SellerResponseDTO validaUpdate(Long id, Seller seller) {
        SellerResponseDTO _sellerFind = getSellerById(id);
        if (_sellerFind != null) {
            //Seller _seller = sellerFind.get();
            deleteSeller(id);
            Seller newSeller = sellerRepository.save(seller);
            SellerResponseDTO sellerResponseDTO = convertEntityToResponse(seller);

            return sellerResponseDTO;


        }else{
            throw new RuntimeException("Seller não encontrado");
        }
    }

    public boolean deleteSeller(Long id){
        SellerResponseDTO sellerFind = getSellerById(id);
        if (sellerFind != null) {
            sellerRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteAllSellers(){

        sellerRepository.deleteAll();
        return true;
    }


    public SellerRequestDTO convertEntityToDTORequest(Seller seller){
        SellerRequestDTO sellerRequestDTO = new SellerRequestDTO();
        sellerRequestDTO.setName(seller.getName());
        sellerRequestDTO.setCpf(seller.getCpf());
        sellerRequestDTO.setEmail(seller.getEmail());
        return sellerRequestDTO;
    }

    public SellerResponseDTO getSellerById(Long id){
        Optional<Seller> _seller  = sellerRepository.findBySellerId(id);
        if (_seller.isPresent()){
            return this.convertEntityToResponse(_seller.get());
        }else {
            throw new RuntimeException("Seller não encontrado!");
        }
    }
}