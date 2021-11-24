package com.meli.projetointegradorgroup1.repository;
import com.meli.projetointegradorgroup1.entity.Product;
import com.meli.projetointegradorgroup1.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Marco Siqueira
 */
@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

    Section findByCode(Long code);
}
