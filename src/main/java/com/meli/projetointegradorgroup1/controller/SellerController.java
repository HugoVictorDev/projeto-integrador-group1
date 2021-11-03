package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.dto.request.SellerRequestDTO;
import com.meli.projetointegradorgroup1.dto.response.SellerResponseDTO;
import com.meli.projetointegradorgroup1.entity.Seller;
import com.meli.projetointegradorgroup1.repository.SellerRepository;
import com.meli.projetointegradorgroup1.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public SellerController(SellerRepository sellerRepository, SellerService sellerService) {
        this.sellerRepository = sellerRepository;
        this.sellerService = sellerService;
    }


    //Cadastrar vendedor
    @PostMapping("/create")
    public SellerResponseDTO createSeller(@Valid @RequestBody SellerRequestDTO sellerRequestDTO){

        Seller seller = sellerService.convertRequestDTOToEntity(sellerRequestDTO);
        seller = sellerService.setSeller(seller);

        SellerResponseDTO sellerResponseDTO = sellerService.convertEntityToResponse(seller);

        return sellerResponseDTO;
    }

    //Consultar lista de  vendeokdores
    @GetMapping("/list")
     public List<SellerResponseDTO> getSellerList() {

        List<Seller> sellerList =  sellerService.getSellers();

        ArrayList<SellerResponseDTO> sellerResponseDTOS = new ArrayList();

        sellerList.stream()
                .forEach(seller -> sellerResponseDTOS.add(sellerService.convertEntityToResponse(seller)));
        return sellerResponseDTOS;
    }

    //busca vendedor pelo id
    @GetMapping("{id}")
    public SellerResponseDTO getSellerById(@PathVariable("id") Long id) {
        return sellerService.convertEntityToResponse(sellerRepository.getById(id));

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
