package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    void deleteById(Long Id);
    Optional<InboundOrder> findById(Long Id);
}
