package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerService sellerService;


    //Cadastrar vendedor - ok
    @PostMapping("/create")
    public SellerResponseDTO createSeller(@Valid @RequestBody Seller seller){

        SellerResponseDTO sellerResponseDTO = sellerService.setSeller(seller);

        return sellerResponseDTO;
    }


    //Consultar lista de  vende
    // dores
    @GetMapping("/list") // - ok
    List<SellerResponseDTO> getSellerList() {

        return sellerService.getSellers();
    }

    //busca vendedor pelo id
    @GetMapping("{id}") // - ok
    public SellerResponseDTO getSellerById(@PathVariable("id") Long id) {
        return sellerService.convertEntityToDTO(sellerRepository.getById(id));

    }

    // atualizando vendedor pelo ID
    //@PutMapping("/update/{id}")
    //public ResponseEntity<HttpStatus> updateSeller(@PathVariable("id") Long id, @Valid @RequestBody SellerRequestDTO sellerRequestDTO) {

        //Optional<Seller> sellerFind = sellerRepository.findById(id);
        //Seller _seller = sellerService.validaUpdate(sellerFind, sellerRequestDTO);
        //return sellerService.convertEntityToDTORequest(sellerRepository.save(_seller));

//    }

    //delete todos vendedores - ok
    @DeleteMapping("/deleteall")
    public ResponseEntity<HttpStatus> deleteAllSellers() {
        return sellerService.delAllSellers();

    }

    //deletar vendedor pelo ID - ok
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSellerById(@PathVariable("id") Long id) {
        //// delete
        return sellerService.delSeller((id));
    }


}