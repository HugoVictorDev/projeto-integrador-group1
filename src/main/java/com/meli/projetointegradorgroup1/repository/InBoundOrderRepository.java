package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InBoundOrderRepository extends JpaRepository<InBoundOrder, Long> {
    //@Modifying
    //@Query("delete from InBoundOrdem ibo where ibo.OrderNumber = ?1")
    Optional<InBoundOrder> deleteByOrderNumber(Long OrderNumber);
    Optional<InBoundOrder> findById(Long Id);


    //void deleteInboundOrderByOrderNumber(Long OrderNumber);
}
