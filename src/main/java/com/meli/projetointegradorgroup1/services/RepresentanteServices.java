package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.RepresentanteDTO;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteServices {

    @Autowired
    RepresentanteRepository representanteRepository;


    public RepresentanteServices(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }


    public void valida(RepresentanteDTO representanteDTO)  {
        validarCpf(representanteDTO.getCpf());
    }

    public void validarCpf(String cpf)  {
        Representante representante = representanteRepository.findAllByCpf(maskCpf(cpf));
        if (representante != null){
            throw new RuntimeException("CPF já cadstrado");
        }
    }

    public static String maskCpf(String cpf) {
       return (cpf.substring(0, 3) + "." +cpf.substring(3, 6) + "." +cpf.substring(6, 9) + "-" +cpf.substring(9, 11));
    }

    public Representante validaUpdate(Representante representatneFind, RepresentanteDTO representatnedto) {
        if (representatneFind.getId() == null){
            throw new RuntimeException("Representante não encontrado");
        }else{
            Representante representanteUpdate = representatneFind;
            representanteUpdate.setName(representatnedto.getName());
            representanteUpdate.setCpf(maskCpf(representatnedto.getCpf()));
            return representanteUpdate;
        }
    }

    public RepresentanteDTO converteToDto(Representante representante) {
        return RepresentanteDTO.builder()
                .cpf(representante.getCpf())
                .name(representante.getName())
                .representatne_Id(representante.getId())
                .build();
    }

    public Representante converte(RepresentanteDTO dto) {
            return Representante.builder()
                    .cpf(maskCpf(dto.getCpf()))
                    .name(dto.getName())
                    .build();
    }

    public List<RepresentanteDTO> converteList(List<Representante> representantes) {
        List<RepresentanteDTO> listRepresentante = new ArrayList<>();
            for (Representante representante: representantes) {
                listRepresentante.add(
                        RepresentanteDTO.builder()
                                .representatne_Id(representante.getId())
                                .name(representante.getName())
                                .cpf(representante.getCpf())
                                .build()
                );
            }
            return listRepresentante;
        }

    public Representante obter(Long id) {
        Representante representante = representanteRepository.findByid(id);
        if (representante  == null){
            throw new RuntimeException("Representante não encontrado");
        }else {
            return representante;
        }
    }

    public Representante save(Representante representante) {
        try {
            representanteRepository.save(representante);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro na gravação do registro:", e );
        }
        return representante;
    }


    public List<Representante> listaRepresentante() {
        List<Representante> representantList = representanteRepository.findAll();
        if(representantList.size() == 0){
            throw new RuntimeException("Não existem Representantes cadastradas");
        }return representantList;
    }

    public void deletaRepresentante(Long id) {
        try{
            representanteRepository.deleteById(id);
        } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("Referential integrity constraint violation")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }

    public Representante obterRepresentanteById(Long id) {
        Optional<Representante> representante = representanteRepository.findById(id);
        if (representante.isPresent()){
            return representante.get();
        }else throw new RuntimeException("representante não encontrada");
    }
}
