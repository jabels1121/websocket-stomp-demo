package com.jaybe.websocketstompserverdemo.security.repositories;

import com.jaybe.websocketstompserverdemo.security.dao.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
}
