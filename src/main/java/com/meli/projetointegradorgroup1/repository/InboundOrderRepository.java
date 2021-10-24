package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

}
