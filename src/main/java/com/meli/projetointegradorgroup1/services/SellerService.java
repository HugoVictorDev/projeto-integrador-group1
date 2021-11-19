package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
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

    @Autowired
    RepresentanteServices representanteServices;

    public SellerService(SellerRepository sellerRepository, RepresentanteServices representanteServices) {
        this.sellerRepository = sellerRepository;
        this.representanteServices = representanteServices;
    }

    public List<SellerResponseDTO> getSellers(){
        List <Seller> list = sellerRepository.findAll();
        if(list.size() != 0) {
            return list
                    .stream()
                    .map(SellerResponseDTO::new)
                    .collect(Collectors.toList());
        }else {
            throw new RuntimeException("Não existem Sellers cadastrados");
        }
    }

/*
    public ResponseEntity<HttpStatus> valida(Long sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
*/

    public Seller validaUpdate(Seller sellerFind, SellerRequestDTO sellerRequestDTO) {
            Seller _seller = sellerFind;
            _seller.setName(sellerRequestDTO.getName());
            _seller.setCpf(representanteServices.maskCpf(sellerRequestDTO.getCpf()));
            _seller.setEmail(sellerRequestDTO.getEmail());
            return _seller;
    }

    public Seller obter(Long id){
        Optional<Seller> _byId = sellerRepository.findById(id);
        if(_byId == null || _byId.equals(Optional.empty())){
            throw new RuntimeException("Seller não encontrado");
        }else {
            return _byId.get();
        }
    }

    public Seller save(Seller seller) {
        try {
            sellerRepository.save(seller);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação do seller:", e );
        }
        return seller;
    }

    public boolean validaCpf(String cpf) {
        Seller seller = sellerRepository.findByCpf(representanteServices.maskCpf(cpf));
        if (seller!= null){
            throw new RuntimeException("Seller já cadastrado");
        }
        return true;
    }


    public void deleta(Long id) {
        try {
            sellerRepository.deleteById(id);
        } catch (RuntimeException e) {
            if (e.getCause().getCause().getMessage().contains("violates foreign key constraint")) {
                throw new RuntimeException("Referential integrity constraint violation");
            } else {
                throw e;
            }
        }
    }


    public Seller convert(SellerRequestDTO dto) {
        return Seller.builder()
                .name(dto.getName())
                .cpf(representanteServices.maskCpf(dto.getCpf()))
                .email(dto.getEmail())
                .build();
    }

    public SellerResponseDTO convertToDto(Seller seller) {
        return SellerResponseDTO.builder()
                .name(seller.getName())
                .cpf(seller.getCpf())
                .email(seller.getEmail())
                .build();
    }
}