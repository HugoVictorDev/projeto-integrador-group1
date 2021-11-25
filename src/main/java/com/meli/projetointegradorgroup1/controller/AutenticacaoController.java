
package com.meli.projetointegradorgroup1.controller;

import com.meli.projetointegradorgroup1.config.TokenService;
import com.meli.projetointegradorgroup1.dto.request.LoginRequest;
import com.meli.projetointegradorgroup1.dto.response.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> createAuthentication(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();
        Authentication authentication = authenticationManager.authenticate(dadosLogin);
        String token = tokenService.geraToken(authentication);
        return ResponseEntity.ok(new TokenResponseDTO(token, "Bearer"));
    }
}