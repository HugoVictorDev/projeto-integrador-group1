
package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.User;
import com.meli.projetointegradorgroup1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUser(username);
        if (userRepository.findByUser(username) != user){
            System.out.println("Usuário nao cadastrado");
        }
        return user;
    }
}