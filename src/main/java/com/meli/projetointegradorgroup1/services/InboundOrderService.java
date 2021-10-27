package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.*;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InboundOrderService {
    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;


    public InBoundOrder saveIBO(InBoundOrder inBoundOrder, Long representativeId ){
        Optional<Representative> _representative = representativeRepository.findById(representativeId);

        if (_representative.isPresent()) {
            Representative representative = _representative.get();
            InBoundOrder _inBoundOrder = inboundOrderRepository.save
                    (new InBoundOrder(representative, LocalDate.now(),
                            new Section(1L, new Warehouse(1L))));
            return _inBoundOrder;
        }
        return null;
    }

    public void deleteIBO(Long inBoundNumber){

        inboundOrderRepository.deleteByOrderNumber(inBoundNumber);

    }
    public void updateIBO(InBoundOrder inBoundOrder){

        inboundOrderRepository.save(inBoundOrder);

    }

    public void ListIBO(InBoundOrder inBoundOrder){

        inboundOrderRepository.save(inBoundOrder);

    }
}
