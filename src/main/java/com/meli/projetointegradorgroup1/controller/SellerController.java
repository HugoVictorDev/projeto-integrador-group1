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
import java.util.stream.Collectors;

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
        this.sellerRepository.save(sellerRequestDTO.build());
        return sellerRequestDTO;
    }

    //Consultar lista de  vendedores
    @GetMapping("/list")
     List<SellerResponseDTO> getSellerList() {
        return sellerService.getSellers().stream()
        .map(SellerResponseDTO::new)
        .collect(Collectors.toList());

    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        Optional<Seller> sellerFind = sellerRepository.findById(id);

        if (sellerFind.isPresent()) {
            return new ResponseEntity<>(sellerFind.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // atualizando vendedor pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id, @RequestBody Seller seller) {
        Optional<Seller> sellerFind = sellerRepository.findById(id);

        if (sellerFind.isPresent()) {
            Seller _seller = sellerFind.get();
            _seller.setName(seller.getName());
            _seller.setCpf(seller.getCpf());
            _seller.setProductList(seller.getProductList()); // tem que ver como fazer a tratativa de edicao da lista.
            return new ResponseEntity<>(sellerRepository.save(_seller), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//delete todos vendedores
@DeleteMapping("/deleteall")
public ResponseEntity<HttpStatus> deleteAllSellers() {
    try {
        sellerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

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



    //tratamento de mensagens de erro do bad request seguindo as regras do VALID
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        );
        return errors;
    }


}
