package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.request.RepresentativeRequestDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServices{

    @Autowired
    private RepresentativeServices RepresentativeServices;

    @Autowired
    WarehouseServices warehouseServices;


    public void valida(RepresentativeRequestDTO representativedto)  {
        validarWarehouse(Long.parseLong(representativedto.getWarehouseID()));
        validarCpf(representativedto.getCpf(), Long.parseLong(representativedto.getWarehouseID()));
    }

    private void validarWarehouse(Long warehouseID){
        warehouseServices.valida(warehouseID);
    }

    private Warehouse obterWarehouse(Long warehouseID){
        return warehouseServices.obterWarehouse(warehouseID);
    }

    private void validarCpf(String cpf, Long warehouseID)  {
        Warehouse warehouse = warehouseServices.obterWarehouse(warehouseID);
        //if(warehouse.getRepresentante().getCpf().equals(cpf)){
        //    throw new RuntimeException("CPF já cadstrado para essa Warehouse");
        //}
    }

    public static String maskCpf(String cpf) {
       return (cpf.substring(0, 3) + "." +cpf.substring(3, 6) + "." +cpf.substring(6, 9) + "-" +cpf.substring(9, 11));
    }

    public Representative validaUpdate(Optional<Representative> representativeFind, RepresentativeRequestDTO representativedto) {
        if (representativeFind.isPresent()) {
            Representative representative = representativeFind.get();
            representative.setName(representativedto.getName());
            representative.setCpf(maskCpf(representativedto.getCpf()));
            return representative;
        }else{
            throw new RuntimeException("Representative não encontrado");
        }
    }

    public Representative findRepresentative(Optional<Representative> representativeFind) {
        if (representativeFind.isPresent()) {
            Representative representative = representativeFind.get();
            return representative;
    }else{
        throw new RuntimeException("Representative não encontrado");
        }
    }

    public List<Representative> listaRepresentative() {
        List<Representative> representativeList = null; //RepresentativeServices.find();
        if(representativeList.size() == 0){
            throw new RuntimeException("Não existem Representantes cadastradas");
        }return representativeList;
    }

    public Representative obter(Long id){
        Optional<Representative> rep = null; //this.RepresentativeServices.findById(id);
        return rep.get();
    }
}
