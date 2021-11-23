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
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
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



    public Seller validaUpdate(Seller sellerFind, SellerRequestDTO sellerRequestDTO) {
        Seller _seller = sellerFind;
        _seller.setName(sellerRequestDTO.getName());
        _seller.setCpf(representanteServices.maskCpf(sellerRequestDTO.getCpf()));
        _seller.setEmail(sellerRequestDTO.getEmail());
        return _seller;
    }

    public List<Seller> listSeller(){
        return sellerRepository.findAll();
    }

    public Seller findSellerById(Long id){
        Optional<Seller> _byId = sellerRepository.findById(id);
        return _byId.get();
    }

    public Seller obter(Long id){
        Optional<Seller> _byId = sellerRepository.findById(id);
        if(_byId == null || _byId.equals(Optional.empty())){
            throw new RuntimeException("Seller não encontrado");
        }else {
            return _byId.get();
        }
    }

    public ResponseEntity<Object> save(Seller seller, UriComponentsBuilder uriBuilder) {
        try {
            sellerRepository.save(seller);
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RuntimeException("Erro na gravação do seller:", e));
        }
        URI uri = uriBuilder.path("/seller/{id}").buildAndExpand(seller.getId()).toUri();
        return ResponseEntity
                .created(uri).body(convertToDto(seller));
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
                .id(seller.getId())
                .name(seller.getName())
                .cpf(seller.getCpf())
                .email(seller.getEmail())
                .build();
    }


}