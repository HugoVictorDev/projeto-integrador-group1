package com.meli.projetointegradorgroup1.repository;
import com.meli.projetointegradorgroup1.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Marco Siqueira
 */


@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long>{

    Representante findByid(Long id);
    Representante findAllByCpf(String maskCpf);
}
