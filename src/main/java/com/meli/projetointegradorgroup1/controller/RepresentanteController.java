package com.meli.projetointegradorgroup1.controller;
import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.services.RepresentanteServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;
/**
 * @author Marco Siqueira
 */

@RestController
@RequestMapping("/representante")
public class RepresentanteController {

    @Autowired
    RepresentanteServices representanteServices;

    public RepresentanteController(RepresentanteServices representanteServices) {
        this.representanteServices = representanteServices;
    }

    @PostMapping("/post")
    @ApiOperation(value = "Cadastrar novo Representante")
    public ResponseEntity<Object>createrepResentante(@Valid @RequestBody RepresentanteDTO representanteDTO, UriComponentsBuilder uriBuilder){
           representanteServices.valida(representanteDTO);
           Representante representante = representanteServices.convert(representanteDTO);
           return representanteServices.save(representante, uriBuilder);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retornar lista de Representantes")
    public List<RepresentanteDTO>getRepresentanteList() {
           return representanteServices.convertList(representanteServices.listaRepresentante());
    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualizar Representante a partir do id")
    public ResponseEntity<Object>updateRepresentante(@Valid @RequestBody RepresentanteDTO representanteDTO, UriComponentsBuilder uriBuilder) {
           Representante representanteFind = representanteServices.obter(representanteDTO.getRepresentatne_Id());
           Representante representante = representanteServices.validaUpdate(representanteFind, representanteDTO);
           return representanteServices.save(representante, uriBuilder);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retornar Representante único a partir do id")
    public RepresentanteDTO getRepresentanteById(@PathVariable("id") Long id) {
           Representante representatne = representanteServices.obter(id);
           return representanteServices.convertToDto(representatne);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deletar Representante a partir do id")
    public RepresentanteDTO deleteRepresentanteById(@PathVariable("id") Long id){
           Representante representante  = representanteServices.obter(id);
           representanteServices.deletaRepresentante(id);
           return representanteServices.convertToDto(representante);
        }
    }

