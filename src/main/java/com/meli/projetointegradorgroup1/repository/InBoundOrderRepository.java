package com.meli.projetointegradorgroup1.repository;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import com.meli.projetointegradorgroup1.entity.Representante;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hugo Victor
 */


@Repository
public interface InBoundOrderRepository extends JpaRepository<InBoundOrder, Long> {
  InBoundOrder findByOrderNumber(Long orderNum);

InBoundOrder findByRepresentante_Id(Long id);
}
