
package com.meli.projetointegradorgroup1.entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    @Id
    @Column(name="username")
    private String user;
    @Column(name="password")
    private String senha;
    @Column(name="enabled")
    private boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.perfis.forEach(perfil-> authorities.add(new SimpleGrantedAuthority(perfil.getNome())));
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.senha;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.user;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
