package com.meli.projetointegradorgroup1.persistence;

import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representative;
import com.meli.projetointegradorgroup1.repository.InBoundOrderRepository;
import com.meli.projetointegradorgroup1.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class InBoundOrderPersistence {

    @Autowired
    private InBoundOrderRepository inboundOrderRepository;
    @Autowired
    private RepresentativeRepository representativeRepository;

    public InBoundOrderPersistence(){

    }

    public InBoundOrder saveInbound(InBoundOrder inBoundOrder, Representative representative){

        InBoundOrder _inBoundOrder = inboundOrderRepository.save(new InBoundOrder(inBoundOrder.getOrderNumber(), representative, inBoundOrder.getBatchStock(), LocalDate.now()));
        return _inBoundOrder;

    }

    public InBoundOrder deleteInbound(Long inBoundNumber) {
        Optional<InBoundOrder> _inBoundOrder = inboundOrderRepository.findById(inBoundNumber);

        if (_inBoundOrder.isPresent()) {
            InBoundOrder inBoundOrder = _inBoundOrder.get();
            Representative representative =  new Representative(representativeRepository.findById(_inBoundOrder.get().getRepresentative().getRepresentative_Id()));

            return inboundOrderRepository.save(new InBoundOrder(inBoundOrder.getOrderNumber(), representative, inBoundOrder.getBatchStock(), LocalDate.now()));

        }
        return null;
    }
}
