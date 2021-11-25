
package com.meli.projetointegradorgroup1.repository;

import com.meli.projetointegradorgroup1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUser(String user);
}

