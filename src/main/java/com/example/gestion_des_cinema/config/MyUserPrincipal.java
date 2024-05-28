package com.example.gestion_des_cinema.config;

import com.example.gestion_des_cinema.dao.entities.Client;
import lombok.Getter;
import lombok.Setter;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class MyUserPrincipal implements UserDetails {
    private Client creatorDTO;
    private List<GrantedAuthority> authorities;
    public MyUserPrincipal(Client creatorDTO, List<GrantedAuthority> authorities) {
        this.creatorDTO = creatorDTO;
        this.authorities = authorities;
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return creatorDTO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
