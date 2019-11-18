package com.jaybe.websocketstompserverdemo.bootstrap;

import com.jaybe.websocketstompserverdemo.security.dao.AppRole;
import com.jaybe.websocketstompserverdemo.security.dao.AppUser;
import com.jaybe.websocketstompserverdemo.security.repositories.AppUserRepository;
import com.jaybe.websocketstompserverdemo.security.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Slf4j
public class DataLoader {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public DataLoader(AppUserRepository appUserRepository,
                      RoleRepository roleRepository,
                      PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @EventListener
    @Transactional
    public void loadData(ContextRefreshedEvent event) {
        log.info("Starting bootstrapping the data.");

        var adminRole = new AppRole();
        adminRole.setRole("ADMIN");

        var userRole = new AppRole();
        userRole.setRole("USER");

        var wsRole = new AppRole();
        wsRole.setRole("WS_USER");

        roleRepository.saveAll(Arrays.asList(adminRole, userRole, wsRole));

        var adminUser = new AppUser();
        adminUser.setUserName("foo");
        adminUser.setPassword(encoder.encode("foo"));
        adminUser.addRole(adminRole);

        var user = new AppUser();
        user.setUserName("user");
        user.setPassword(encoder.encode("pass"));
        user.addRole(userRole);

        var wsUser = new AppUser();
        wsUser.setUserName("wsuser");
        wsUser.setPassword(encoder.encode("pass"));
        wsUser.addRole(wsRole);

        appUserRepository.saveAll(Arrays.asList(adminUser, user, wsUser));

        log.info("Ending of bootstrapping the data.");
    }
}
