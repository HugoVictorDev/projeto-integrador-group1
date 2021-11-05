package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.response.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representante;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepresentativeServices{

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    WarehouseServices warehouseServices;


    public void valida(RepresentativeDTO representativedto)  {
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
        if(warehouse.getRepresentante().getCpf().equals(cpf)){
            throw new RuntimeException("CPF já cadstrado para essa Warehouse");
        }
    }

    public static String maskCpf(String cpf) {
       return (cpf.substring(0, 3) + "." +cpf.substring(3, 6) + "." +cpf.substring(6, 9) + "-" +cpf.substring(9, 11));
    }

    public Representante validaUpdate(Optional<Representante> representativeFind, RepresentativeDTO representativedto) {
        if (representativeFind.isPresent()) {
            Representante representative = representativeFind.get();
            representative.setName(representativedto.getName());
            representative.setCpf(maskCpf(representativedto.getCpf()));
            return representative;
        }else{
            throw new RuntimeException("Representante não encontrado");
        }
    }

    public Representante findRepresentative(Optional<Representante> representativeFind) {
        if (representativeFind.isPresent()) {
            Representante representative = representativeFind.get();
            return representative;
    }else{
        throw new RuntimeException("Representante não encontrado");
        }
    }

    public List<Representante> listaRepresentative() {
        List<Representante> representativeList = representanteRepository.findAll();
        if(representativeList.size() == 0){
            throw new RuntimeException("Não existem Representantes cadastradas");
        }return representativeList;
    }

    public Representante obter(Long id){
        Optional<Representante> rep = this.representanteRepository.findById(id);
        return rep.get();
    }
}
