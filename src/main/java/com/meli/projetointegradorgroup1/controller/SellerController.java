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

    //Cadastrar vendedor
    @PostMapping("/create")
    public SellerRequestDTO createSeller(@Valid @RequestBody SellerRequestDTO sellerRequestDTO){
        this.sellerRepository.save(sellerRequestDTO.converte(sellerRequestDTO));
        return sellerRequestDTO;
    }

    //Consultar lista de  vendeokdores
    @GetMapping("/list")
     List<SellerResponseDTO> getSellerList() {
        return sellerService.getSellers();
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public SellerResponseDTO getSellerById(@PathVariable("id") Long id) {
        return sellerService.convertEntityToDTO(sellerRepository.getById(id));

    }

    // atualizando vendedor pelo ID
    @PutMapping("/update/{id}")
    public SellerRequestDTO updateSeller(@PathVariable("id") Long id, @Valid @RequestBody SellerRequestDTO sellerRequestDTO) {

        Optional<Seller> sellerFind = sellerRepository.findById(id);
        Seller _seller = sellerService.validaUpdate(sellerFind, sellerRequestDTO);
        return sellerService.convertEntityToDTORequest(sellerRepository.save(_seller));

    }

//delete todos vendedores
@DeleteMapping("/deleteall")
public Seller deleteAllSellers() {
        sellerRepository.deleteAll();
        return null;

}

//deletar vendedor pelo ID
@DeleteMapping("/delete/{id}")
public ResponseEntity<HttpStatus> deleteSellerById(@PathVariable("id") Long id) {
    try {
        sellerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}
