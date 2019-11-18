package com.jaybe.websocketstompserverdemo.security.dao;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDetailsImpl implements UserDetails {

    private AppUser appUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null != this.appUser) {
            Set<AppRole> authorities = this.appUser.getAuthorities();
            return Objects.requireNonNullElse(authorities, Collections.emptyList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getPassword() {
        return this.appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUser.getUserName();
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
