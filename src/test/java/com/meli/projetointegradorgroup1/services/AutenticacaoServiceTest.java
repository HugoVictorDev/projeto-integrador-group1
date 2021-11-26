package com.meli.projetointegradorgroup1.services;

import com.meli.projetointegradorgroup1.entity.User;
import com.meli.projetointegradorgroup1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AutenticacaoServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    User user = new User();

    @Test
    public void loadUserByUsername(){
        Mockito.when(userRepository.findByUser(Mockito.anyString())).thenReturn(user);
        AutenticacaoService autenticacaoService = new AutenticacaoService(userRepository);
        assert(autenticacaoService.loadUserByUsername("User") != null);
    }
}
