package com.jaybe.websocketstompserverdemo.security.services;

import com.jaybe.websocketstompserverdemo.security.dao.UserDetailsImpl;
import com.jaybe.websocketstompserverdemo.security.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var appUser = appUserRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found user by name=" + username));
        return new UserDetailsImpl(appUser);
    }
}
