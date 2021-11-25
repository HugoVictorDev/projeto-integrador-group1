package com.meli.projetointegradorgroup1.repository;
import com.meli.projetointegradorgroup1.entity.InBoundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Hugo Victor
 */


@Repository
public interface InBoundOrderRepository extends JpaRepository<InBoundOrder, Long> {
  InBoundOrder findByOrderNumber(Long orderNum);
  InBoundOrder findByRepresentante_Id(Long id);
}
