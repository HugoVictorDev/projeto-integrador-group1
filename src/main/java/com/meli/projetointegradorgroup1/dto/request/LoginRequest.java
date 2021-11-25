
package com.meli.projetointegradorgroup1.dto.request;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginRequest {

    private String user;
    private String senha;

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(user, senha);
    }
}
