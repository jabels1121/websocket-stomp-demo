package com.jaybe.websocketstompserverdemo.security.repositories;

import com.jaybe.websocketstompserverdemo.security.dao.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUserName(String userName);

}
