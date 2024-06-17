package com.example.compl.Service;

import com.example.compl.Models.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor

public class UserDetailsImp implements UserDetails {
    public String username;
    public String password;
    public Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImp build(UserModel userModel) {
        List<GrantedAuthority> authorityList = userModel.getRoles().stream().map(roleModel -> new SimpleGrantedAuthority("ROLE_"+roleModel.name()))
                .collect(Collectors.toList());
        return new UserDetailsImp(userModel.getUsername(), userModel.getPassword(), authorityList);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
