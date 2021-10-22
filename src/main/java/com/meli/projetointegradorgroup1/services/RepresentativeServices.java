package com.meli.projetointegradorgroup1.services;
import com.meli.projetointegradorgroup1.dto.RepresentativeDTO;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.entity.Warehouse;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import com.meli.projetointegradorgroup1.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeServices{

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;


    public void valida(RepresentativeDTO representativedto)  {
        validaWarehouse(Long.parseLong(representativedto.getWarehouseID()));
        validaCpf(representativedto.getCpf(), Long.parseLong(representativedto.getWarehouseID()));
    }

    private void validaWarehouse(Long warehouseID){
           Warehouse warehouse =  warehouseRepository.findBywarehouseId(warehouseID);
           if (warehouse == null){
               throw new RuntimeException("Warehouse não cadastrada");
           }
    }

    private void validaCpf(String cpf, Long warehouseID)  {
            Representative representative = representativeRepository.findAllByCpfAndWarehouse_WarehouseId(maskCpf(cpf), warehouseID);
            if (representative != null && representative.getWarehouse().getWarehouseId() == warehouseID){
                throw new RuntimeException("CPF já cadstrado para essa Warehouse");
            }
    }


    public static String maskCpf(String cpf) {
       return (cpf.substring(0, 3) + "." +cpf.substring(3, 6) + "." +cpf.substring(6, 9) + "-" +cpf.substring(9, 11));
    }
}
