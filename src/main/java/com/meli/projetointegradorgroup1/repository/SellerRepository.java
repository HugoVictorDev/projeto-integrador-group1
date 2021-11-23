package com.meli.projetointegradorgroup1.repository;


import com.meli.projetointegradorgroup1.entity.BatchStock;
import com.meli.projetointegradorgroup1.entity.Seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByCpf(String cpf);
    Optional<Seller> findById(Long Id);
    List<Seller> findAll();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Seller s set s.name =:name, s.cpf =:cpf, s.email =:email where s.id =:id")
    int setSellerInfoById(@Param("name") String name, @Param("cpf") String cpf,@Param("email") String email, @Param("id") Long id);

}
