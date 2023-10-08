package com.mercadonarest.infraestructure.postgres.daos;

import com.mercadonarest.infraestructure.postgres.entities.ERole;
import com.mercadonarest.infraestructure.postgres.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
