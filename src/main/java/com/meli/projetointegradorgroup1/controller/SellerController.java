package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;

    //Cadastrar vendedor
    @PostMapping("/create")
    public ResponseEntity<Seller> createSeller (@RequestBody Seller seller){
        try {
            Seller _seller = sellerRepository.save(new Seller(seller.getName(), seller.getCpf(), null));
           return new ResponseEntity<>(_seller, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Consultar lista de  vendeokdores
    @GetMapping("/list")
    public ResponseEntity<List<Seller>> getSellerList() {
        try {
            List<Seller> sellers = new ArrayList<Seller>();

            sellerRepository.findAll().forEach(sellers::add);

            return new ResponseEntity<>(sellers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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


}
