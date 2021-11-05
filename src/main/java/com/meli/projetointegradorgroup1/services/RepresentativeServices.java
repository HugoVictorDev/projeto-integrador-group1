package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServices{

    @Autowired
    RepresentativeRepository representativeRepository;

    @Autowired
    WarehouseServices warehouseServices;


    public RepresentativeServices(WarehouseServices warehouseServices, RepresentativeRepository representativeRepository) {
        this.warehouseServices = warehouseServices;
        this.representativeRepository = representativeRepository;
    }


    public void valida(RepresentativeDTO representativedto)  {
        validarWarehouse(Long.parseLong(representativedto.getWarehouseID()));
        validarCpf(representativedto.getCpf(), Long.parseLong(representativedto.getWarehouseID()));
    }

    public void validarWarehouse(Long warehouseID){
        warehouseServices.valida(warehouseID);
    }

    public Warehouse obterWarehouse(Long warehouseID){
        return warehouseServices.obterWarehouse(warehouseID);
    }

    public void validarCpf(String cpf, Long warehouseID)  {
        Representative representative = representativeRepository.findAllByCpfAndWarehouse_WarehouseId(maskCpf(cpf), warehouseID);
        if (representative != null && representative.getWarehouse().getWarehouseId() == warehouseID){
            throw new RuntimeException("CPF já cadstrado para essa Warehouse");
        }
    }

    public static String maskCpf(String cpf) {
       return (cpf.substring(0, 3) + "." +cpf.substring(3, 6) + "." +cpf.substring(6, 9) + "-" +cpf.substring(9, 11));
    }

    public Representative validaUpdate(Optional<Representative> representativeFind, RepresentativeDTO representativedto) {
        if (representativeFind.get().getRepresentative_Id() == null){
            throw new RuntimeException("Representante não encontrado");
        }else{
            Representative representative = representativeFind.get();
            representative.setName(representativedto.getName());
            representative.setCpf(maskCpf(representativedto.getCpf()));
            representative.setWarehouse(obterWarehouse(Long.parseLong(representativedto.getWarehouseID())));
            return representative;
        }
    }

    public Representative findRepresentative(Optional<Representative> representativeFind) {
        if (representativeFind.get().getRepresentative_Id() == null){
            throw new RuntimeException("Representante não encontrado");
    }else{
            Representative representative = representativeFind.get();
            return representative;
        }
    }

    public List<Representative> listaRepresentative() {
        List<Representative> representativeList = representativeRepository.findAll();
        if(representativeList.size() == 0){
            throw new RuntimeException("Não existem Representantes cadastradas");
        }return representativeList;
    }

    public RepresentativeDTO converteToDto(Representative representative) {
            return new RepresentativeDTO(representative.getRepresentative_Id(), representative.getName(), representative.getCpf(),
                    Long.toString(representative.getWarehouse().getWarehouseId()));
    }

    public Representative converte(RepresentativeDTO dto) {
            return new  Representative().Name(dto.getName())
                    .Cpf(RepresentativeServices.maskCpf(dto.getCpf()))
                    .WarehouseID(Long.parseLong(dto.getWarehouseID()));
    }

    public List<RepresentativeDTO> converteList(List<Representative> representatives) {
        List<RepresentativeDTO> listRepresentant = new ArrayList<>();
            for (Representative representative: representatives) {
                listRepresentant.add(new RepresentativeDTO(representative.getRepresentative_Id(), representative.getName(),
                        representative.getCpf(), Long.toString(representative.getWarehouse().getWarehouseId())));
            }
            return listRepresentant;
        }

    public void deletaRepresentative(Long id) {
        try{
            representativeRepository.deleteById(id);
            } catch (RuntimeException e) {
            if(e.getCause().getCause().getMessage().contains("Referential integrity constraint violation")){
                throw new RuntimeException("Referential integrity constraint violation");
            }else {
                throw e;
            }
        }
    }
}
