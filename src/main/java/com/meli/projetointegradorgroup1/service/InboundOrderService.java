package com.meli.projetointegradorgroup1.service;

import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.persistence.InBoundOrderPersistence;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class InboundOrderService {
    @Autowired
    private RepresentativeRepository representativeRepository;
    @Autowired
    private InBoundOrderPersistence inBPersistence;


    public InBoundOrder saveIBO(InBoundOrder inBoundOrder, Long representativeId ){
        Optional<Representative> _representative = representativeRepository.findById(representativeId);

        if (_representative.isPresent()) {
            Representative representative = _representative.get();
            return inBPersistence.saveInbound(inBoundOrder, representative);
        }
        return null;
    }


}
