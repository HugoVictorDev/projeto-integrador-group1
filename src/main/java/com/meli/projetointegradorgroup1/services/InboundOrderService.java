package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class InboundOrderService {
    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;


    public InBoundOrder saveIBO(InBoundOrder inBoundOrder, Long representativeId ){
        Optional<Representative> _representative = representativeRepository.findById(representativeId);

        if (_representative.isPresent()) {
            Representative representative = _representative.get();
            InBoundOrder _inBoundOrder = inboundOrderRepository.save(new InBoundOrder(inBoundOrder.getOrderNumber(), representative,
                    (BatchStock) inBoundOrder.getBatchStock(), LocalDate.now()));
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
